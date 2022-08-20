package com.knight.javaPractice.controller.concern;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 全局异常处理
@ControllerAdvice(basePackages = "com.knight.javaPractice.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultData<String> validationError(MethodArgumentNotValidException e) {
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : e.getAllErrors()) {
            // 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(objectError.getDefaultMessage());
        }
        return ResultData.fail(400, detailMessage.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultData<String> exceptionHandler(Exception e) {
        return ResultData.fail(500, e.getMessage());
    }
}
