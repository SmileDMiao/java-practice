package com.knight.javaPractice.helper;

// API Code Message
public enum ReturnCode {

    RC200(200, "OK"),
    RC400(400, "Bad Request"),
    RC500(500, "Internal Server Error");

    private final int code;
    private final String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
