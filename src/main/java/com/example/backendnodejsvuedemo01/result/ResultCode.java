package com.example.backendnodejsvuedemo01.result;

/**
 * @Author Nick
 * @Classname ResultCode
 * @Date 2021/8/9 15:20
 * @Description
 */
public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
