package com.knight.javapractice.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.knight.javapractice.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultData<String> validationError(HttpServletRequest req, MethodArgumentNotValidException e) {
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : e.getAllErrors()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(objectError.getDefaultMessage());
        }
        return ResultData.fail(500, detailMessage.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultData<String> exceptionHandler(HttpServletRequest req, Exception e) {
        return ResultData.fail(500, e.getMessage());
    }
}
