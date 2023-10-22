package com.mjc.school.controller.handler;

import com.mjc.school.service.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private HttpStatus httpStatus;

    @ExceptionHandler(value = {InvalidDataException.class, NotFoundException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){
        if(ex instanceof InvalidDataException){
            httpStatus = HttpStatus.BAD_REQUEST;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return handleExceptionInternal(ex,
                ex.getMessage(),
                new HttpHeaders(),
                httpStatus,request);


    }

    @ResponseBody
    @ExceptionHandler({ParameterParsingException.class, RequiredParameterDidNotSetException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDto requestParameterExceptionHandler(RequestException ex) {
        return ErrorDto.getBuild()
                .setCode(ex.getCode())
                .setMessage(ex.getMessage())
                .build();
    }

}
