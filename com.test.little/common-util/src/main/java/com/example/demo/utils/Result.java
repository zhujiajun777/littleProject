package com.example.demo.utils;

import com.example.demo.model.ResultEnum;

import java.io.Serializable;

/**
 * TODO: 类描述
 *
 * @author zhujiajun
 * @date 2020/8/27 17:11
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String returnCode;
    private String returnMsg;
    private T data;

    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnMsg() {
        return returnMsg;
    }
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString(){
        if(data!=null) {
            return "returnCode:" + returnCode + ",returnMsg:" + returnMsg + ",data:" + data;
        }else {
            return "returnCode:" + returnCode + ",returnMsg:" + returnMsg;
        }
    }

    public Result(String returnCode, String returnMsg, T data) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.data = data;
    }

    public Result() {
    }

    public Result(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public static Result successResult(Object object){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),object);
    }

    public  static Result successResult(){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }


    public static Result failResult(ResultEnum resultEnum){
        return new Result(resultEnum.getCode(),resultEnum.getMsg());
    }

}
