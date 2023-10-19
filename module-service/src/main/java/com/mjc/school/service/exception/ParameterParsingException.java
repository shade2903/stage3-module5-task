package com.mjc.school.service.exception;

public class ParameterParsingException extends RequestException {
    private final String parameterName;

    public ParameterParsingException(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getCode() {
        return "0004";
    }

    @Override
    public String getMessage() {
        return "Parsing request error " + this.parameterName;
    }
}
