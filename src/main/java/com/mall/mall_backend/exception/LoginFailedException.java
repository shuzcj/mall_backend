package com.mall.mall_backend.exception;


public class LoginFailedException extends RuntimeException {

    public enum Reason {
        USERNAME_NOT_FOUND,
        WRONG_PASSWORD,
        USERNAME_ALREADY_EXISTS
    }

    private final Reason reason;


    public LoginFailedException(Reason reason) {
        super(reason.name());
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

}
