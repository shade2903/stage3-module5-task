package com.mjc.school.service.exception;

public abstract class RequestException extends RuntimeException{
    public abstract String getCode();
    @Override
    public abstract String getMessage();
}
