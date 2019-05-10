package com.nut.chineseworkday.functions;

import com.alibaba.fastjson.JSON;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.PojoRequestHandler;
import com.nut.chineseworkday.pojo.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.SUNDAY;

public class DateIsHolidayHandlerForApiGateway implements PojoRequestHandler<ApiGatewayRequest, ApiGatewayResponse> {
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
        context.getLogger().debug("apiGatewayRequest is " + JSON.toJSONString(request));
        String body = request.getBody();
        if(request.getIsBase64Encoded()){
            body = new String(Base64.getDecoder().decode(body.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        }
        DateIsHolidayRequest dateIsHolidayRequest = JSON.parseObject(body,DateIsHolidayRequest.class);
        DateIsHolidayHandler handler = new DateIsHolidayHandler();
        Response<DateIsHolidayResponseData> response = handler.handleRequest(dateIsHolidayRequest, context);
        return ApiGatewayResponse.createApiGatewayResponse(response);
    }

}
