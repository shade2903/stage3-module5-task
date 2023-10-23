package com.mjc.school.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidatorException  extends RuntimeException{
    public ValidatorException(String message){
        super(message);
    }
}
