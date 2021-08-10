package com.example.backendnodejsvuedemo01.result;

/**
 * @Author Nick
 * @Classname ResultFactory
 * @Date 2021/8/9 15:18
 * @Description
 */
public class ResultFactory {
    public static Result buildSuccessResult(Object data){
        return buildResult(ResultCode.SUCCESS,"成功！",data);
    }

    public static Result buildFailResult(String message){
        return buildResult(ResultCode.FAIL,message,null);
    }

    public static Result buildResult(ResultCode code, String message, Object data){
        return buildResult(code.code,message,data);
    }

    public static Result buildResult(int resultCode,String message,Object data){
        return new Result(resultCode,message,data);
    }

//    public static Result buildSuccessResult(Object data) {
//        return buildResult(ResultCode.SUCCESS, "成功", data);
//    }
//
//    public static Result buildFailResult(String message) {
//        return buildResult(ResultCode.FAIL, message, null);
//    }
//
//    public static Result buildResult(ResultCode resultCode, String message, Object data) {
//        return buildResult(resultCode.code, message, data);
//    }
//
//    public static Result buildResult(int resultCode, String message, Object data) {
//        return new Result(resultCode, message, data);
//    }
}
