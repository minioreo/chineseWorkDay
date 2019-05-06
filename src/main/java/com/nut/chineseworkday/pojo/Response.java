package com.nut.chineseworkday.pojo;

public class Response<T> {
    private boolean success;
    private String errorCode;
    private String errorMessage;
    private T data;

    public boolean isSuccess() {
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
