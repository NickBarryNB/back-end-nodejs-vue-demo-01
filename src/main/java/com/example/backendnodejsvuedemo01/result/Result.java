package com.example.backendnodejsvuedemo01.result;

import lombok.Data;

/**
 * @Author Nick
 * @Classname Result
 * @Date 2021/8/3 18:31
 * @Description Result 类是为了构造 response，主要是响应码
 */
@Data
public class Result {
    //响应码
    //实际上由于响应码是固定的，code 属性应该是一个枚举值，这里作了一些简化。
    private int code;
    private String message;
    private Object result;

    public Result(int code) {
        this.code = code;
    }

    public Result(int resultCode, String message, Object data) {
        this.code = resultCode;
        this.message = message;
        this.result = data;
    }

}
