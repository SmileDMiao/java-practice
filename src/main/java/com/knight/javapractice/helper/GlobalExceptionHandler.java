package com.knight.javapractice.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "package com.knight.javapractice.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultData<String> exceptionHandler(HttpServletRequest req, Exception e) {
        return ResultData.fail(500, e.getMessage());
    }
}
