package com.mjc.school.controller.handler;

import com.mjc.school.controller.exception.ErrorResponse;
import com.mjc.school.service.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.mjc.school.service.exception.ErrorCode.VALIDATION_DATA;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private HttpStatus httpStatus;

    @ExceptionHandler(value = {InvalidDataException.class, NotFoundException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        if (ex instanceof InvalidDataException || ex instanceof ValidatorException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return handleExceptionInternal(ex,
                ex.getMessage(),
                new HttpHeaders(),
                httpStatus, request);

    }

    @ExceptionHandler(value = ValidatorException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException(Exception ex) {
        return buildErrorResponse(VALIDATION_DATA.getErrorCode(),
                String.format(VALIDATION_DATA.getMessage(),
                        ex.getMessage()),
                ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            String code,
            String errorMessage,
            String errorDetails,
            HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(code, errorMessage, errorDetails), status);
    }

}



