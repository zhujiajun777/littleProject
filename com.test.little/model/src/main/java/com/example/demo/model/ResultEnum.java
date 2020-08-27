package com.example.demo.model;

/**
 * TODO: result 信息枚举类
 * @author zhujiajun
 * @date 2020/8/27 16:29
 */
public enum  ResultEnum {

    SUCCESS("00000","成功!"),
    SYSTEM_ERROR("22222","失败!");

    private String code;

    private String msg;

    ResultEnum(String code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
