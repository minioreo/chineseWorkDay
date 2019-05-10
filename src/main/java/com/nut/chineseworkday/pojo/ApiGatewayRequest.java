package com.nut.chineseworkday.pojo;


import java.util.Map;

public class ApiGatewayRequest {
    private String path;

    private String httpMethod;

    private Map headers;

    private Map queryParameters;

    private Map pathParameters;

    private String body;

    private boolean isBase64Encoded;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map getHeaders() {
        return headers;
    }

    public void setHeaders(Map headers) {
        this.headers = headers;
    }

    public Map getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(Map queryParameters) {
        this.queryParameters = queryParameters;
    }

    public Map getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(Map pathParameters) {
        this.pathParameters = pathParameters;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean getIsBase64Encoded() {
        return isBase64Encoded;
    }

    public void setIsBase64Encoded(boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }
}
