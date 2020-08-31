package com.example.demo.utils;

import com.example.demo.model.ResultEnum;

/**
 * TODO: Result返回信息封装类
 * @author zhujiajun
 * @date 2020/8/27 16:58
 */
public class ResultUtils {

    public static Result<Object> error(String returnCode, String returnMsg) {
        Result<Object> result = new Result<Object>();
        result.setReturnCode(returnCode);
        result.setReturnMsg(returnMsg);
        return result;
    }

    public static Result<Object> error(ResultEnum resultEnum) {
        Result<Object> result = new Result<Object>();
        result.setReturnCode(resultEnum.getCode());
        result.setReturnMsg(resultEnum.getMsg());
        return result;
    }

    public static Result<Object> sucess(Object object) {
        Result<Object> result = new Result<Object>();
        result.setReturnCode(ResultEnum.SUCCESS.getCode());
        result.setReturnMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result<Object> error(String message) {
        Result<Object> result = new Result<Object>();
        result.setReturnCode(ResultEnum.SYSTEM_ERROR.getCode());
        result.setReturnMsg(ResultEnum.SYSTEM_ERROR.getMsg());
        result.setData(message);
        return result;
    }

}
