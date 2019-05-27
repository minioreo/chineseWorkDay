package com.nut.chineseworkday.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Response<T> {
    @JSONField(name = "success")
    private boolean success;
    @JSONField(name = "errorCode")
    private String errorCode;
    @JSONField(name = "errorMessage")
    private String errorMessage;
    private T data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getIsSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getData() {
        return data;
    }

    public static <T> Response<T> createSuccessResponse(T data){
        Response<T> response = new Response<T>();
        response.success = true;
        response.data = data;
        return response;
    }

    public static <T> Response<T> createFailResponse(String errorCode, String errorMessage){
        Response<T> response = new Response<T>();
        response.success = false;
        response.errorCode = errorCode;
        response.errorMessage = errorMessage;
        return response;
    }
}
