package com.knight.javaPractice.controller.concern;

import java.io.Serializable;

import lombok.Data;

// 接口统一返回格式
@Data
public class ResultData<T> implements Serializable {

    private int status;
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC200.getCode());
        resultData.setMessage(ReturnCode.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }

}
