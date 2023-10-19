package com.mjc.school.service.dto;

public class ErrorDto {
    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder getBuild(){
        return new Builder();
    }

    public static class Builder{
        public final  ErrorDto errorDto;
        private Builder(){
            errorDto = new ErrorDto();

        }

        public Builder setCode(String code){
           errorDto.code = code;
            return this;
        }

        public Builder setMessage(String message){
            errorDto.message = message;
            return this;
        }

        public ErrorDto build(){
            return  errorDto;
        }
    }
}
