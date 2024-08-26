package com.mall.common.exception;

import com.mall.common.vo.ApiResponse;
import com.mall.common.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理用户已存在的异常
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ApiResponse<Void> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ApiResponse.badRequest(ex.getMessage());
    }

    // 处理登录失败的异常
    @ExceptionHandler(LoginFailedException.class)
    public ApiResponse<Void> handleLoginFailedException(LoginFailedException ex) {
        return ApiResponse.unauthorized(ex.getMessage());
    }

    // 处理用户未登录的异常
    @ExceptionHandler(UserNotLoggedInException.class)
    public ApiResponse<Void> handleUserNotLoggedInException(UserNotLoggedInException ex) {
        return ApiResponse.unauthorized(ex.getMessage());
    }
    // 其他异常处理方法可以在这里添加
}
