package com.example.demo.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.Map;

/**
 * TODO: 将map入参匹配到swagger文档的工具类
 *
 * @author zhujiajun
 * @date 2020/8/11 14:01
 */
@Component
public class MapReaderForApi implements ParameterBuilderPlugin {

    @Autowired
    private TypeResolver typeResolver;

    //动态生成的虚拟DTO Class包路径
    private final static String basePackage = "com.example.demo.";

    @Override
    public void apply(ParameterContext parameterContext) {

        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();

        if (methodParameter.getParameterType().canCreateSubtype(Map.class)
                || methodParameter.getParameterType().canCreateSubtype(String.class)) {
            //判断是否需要修改对象的ModelRef
            //此处判断Map类型和String类型

            Optional<ApiJsonObject> optional = methodParameter.findAnnotation(ApiJsonObject.class);
            //根据参数上的ApiJsonObject注解中的参数动态生成Class

            if (optional.isPresent()) {

                String name = optional.get().name();
                //model名称

                ApiJsonProperty[] properties = optional.get().value();

                parameterContext.getDocumentationContext().getAdditionalModels()
                        .add(typeResolver.resolve(createRefModel(properties, name)));
                //向documentContext的Models中添加我们新生成的Class

                parameterContext.parameterBuilder()
                        .parameterType("body").modelRef(new ModelRef(name)).name(name);
                //修改Map参数的ModelRef为动态生成的class
            }
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return false;
    }

    /**
     * 根据propertys中的值动态生成含有Swagger注解的javaBeen
     */
    private Class createRefModel(ApiJsonProperty[] propertys, String name) {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(basePackage + name);

        try {
            for (ApiJsonProperty property : propertys) {
                ctClass.addField(createField(property, ctClass));
            }
            return ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据property的值生成含有swagger apiModelProperty注解的属性
     */
    private CtField createField(ApiJsonProperty property, CtClass ctClass)
            throws NotFoundException, CannotCompileException {
        CtField ctField = new CtField(getFieldType(property.type()), property.key(), ctClass);
        ctField.setModifiers(Modifier.PUBLIC);

        ConstPool constPool = ctClass.getClassFile().getConstPool();

        AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation ann = new Annotation("io.swagger.annotations.ApiModelProperty", constPool);
        ann.addMemberValue("value", new StringMemberValue(property.description(), constPool));
        // string类型
        if (ctField.getType().subclassOf(ClassPool.getDefault().get(String.class.getName()))) {
            ann.addMemberValue("example", new StringMemberValue(property.example(), constPool));
        }
        // int类型
        if (ctField.getType().subclassOf(ClassPool.getDefault().get(Integer.class.getName()))) {
            ann.addMemberValue("example", new IntegerMemberValue(Integer.parseInt(property.example()), constPool));
        }
        // double类型
        if (ctField.getType().subclassOf(ClassPool.getDefault().get(Double.class.getName()))) {
            ann.addMemberValue("example", new DoubleMemberValue(Double.parseDouble(property.example()), constPool));
        }
        ann.addMemberValue("required", new BooleanMemberValue(property.required(), constPool));

        attr.addAnnotation(ann);
        ctField.getFieldInfo().addAttribute(attr);

        return ctField;
    }

    /**
     * 参数类型
     *
     * @param type
     * @return
     * @throws NotFoundException
     */
    private CtClass getFieldType(String type) throws NotFoundException {
        CtClass fileType = null;
        switch (type) {
            case "string":
                fileType = ClassPool.getDefault().get(String.class.getName());
                break;
            case "int":
                fileType = ClassPool.getDefault().get(Integer.class.getName());
            case "double":
                fileType = ClassPool.getDefault().get(Double.class.getName());
                break;
        }
        return fileType;
    }

}
