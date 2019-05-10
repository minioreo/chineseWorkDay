package com.nut.chineseworkday.pojo;

import com.alibaba.fastjson.JSON;

public class ApiGatewayResponse {
    private boolean isBase64Encoded;

    private int statusCode;

    private String headers;

    private String body;

    public static ApiGatewayResponse createApiGatewayResponse(Response response){
        ApiGatewayResponse apiGatewayResponse = new ApiGatewayResponse();
        apiGatewayResponse.isBase64Encoded = false;
        apiGatewayResponse.statusCode = 200;
        apiGatewayResponse.body = JSON.toJSONString(response);
        return apiGatewayResponse;
    }

    public boolean getIsBase64Encoded() {
        return isBase64Encoded;
    }

    public void setIsBase64Encoded(boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
