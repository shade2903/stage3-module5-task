package com.mjc.school.service.exception;

public enum ErrorCode {
    NOT_FOUND_DATA(Constants.ERROR_404,"%s with id %d does not exist."),
    VALIDATE_STRING_LENGTH(Constants.ERROR_400, "%s can not be less than %d and more than %d symbols. %s is %s"),
    VALIDATE_STRING_NULL(Constants.ERROR_400, "%s can not be null. %s is null"),

    VALIDATE_NEGATIVE_OR_NULL_NUMBER(Constants.ERROR_400, "%s can not be null or less than 1. %s is: %s"),

    VALIDATION_DATA(Constants.VALIDATION_ERROR_CODE, "Validation failed: %s");



    private final String errorMessage;
    private final String errorCode;

    ErrorCode(String errorCode, String msg){
        this.errorCode = errorCode;
        this.errorMessage = "ERROR_CODE:" + errorCode + " ERROR_MESSAGE: " + msg;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
    private static class Constants{
        private static final String ERROR_400 = "400";
        private static final String ERROR_404 = "404";

        private static final String VALIDATION_ERROR_CODE = "40010";
        private Constants() {}

    }

}
