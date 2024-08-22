package com.mall.mall_backend.exception;

import com.mall.mall_backend.domain.vo.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ApiResponse handleLoginFailedException(LoginFailedException ex) {
        String message;
        if (ex.getReason() == LoginFailedException.Reason.USERNAME_NOT_FOUND) {
            message = "用户名不存在";
            return  ApiResponse.unauthorized(message);
        } else if(ex.getReason() == LoginFailedException.Reason.WRONG_PASSWORD) {
            message = "密码错误";
            return  ApiResponse.unauthorized(message);
        } else {
            message = "用户名已存在";
            return  ApiResponse.badRequest(message);
        }
    }

}
