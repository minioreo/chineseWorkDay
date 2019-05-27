package com.nut.chineseworkday.functions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.PojoRequestHandler;
import com.aliyuncs.fc.client.FunctionComputeClient;
import com.aliyuncs.fc.config.Config;
import com.aliyuncs.fc.request.InvokeFunctionRequest;
import com.aliyuncs.fc.response.InvokeFunctionResponse;
import com.nut.chineseworkday.configuration.PropertyConfig;
import com.nut.chineseworkday.pojo.ApiGatewayRequest;
import com.nut.chineseworkday.pojo.ApiGatewayResponse;
import com.nut.chineseworkday.pojo.DateIsHolidayResponseData;
import com.nut.chineseworkday.pojo.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class DateIsHolidayHandlerForApiGateway implements PojoRequestHandler<ApiGatewayRequest, ApiGatewayResponse> {
    private com.nut.chineseworkday.configuration.Config localConfig = new PropertyConfig("config.properties");

    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
        context.getLogger().debug("apiGatewayRequest is " + JSON.toJSONString(request));
        String body = request.getBody();
        if(request.getIsBase64Encoded()){
            body = new String(Base64.getDecoder().decode(body.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        }
        InvokeFunctionRequest invkReq = new InvokeFunctionRequest("ChineseWorkDayService", "DateIsHolidayFunction");
        invkReq.setPayload(body.getBytes());
        InvokeFunctionResponse invkResp;
        try {
            invkResp = getFunctionComputeClient(context).invokeFunction(invkReq);
        }
        catch(Exception e){
            e.printStackTrace();
            return ApiGatewayResponse.createApiGatewayResponse(Response.createFailResponse("FC Call Error","Fail to call non gateway function"));
        }
        String responseString = new String(invkResp.getContent());
        context.getLogger().debug("response from inner handler is" + responseString);
        Response<DateIsHolidayResponseData> response = JSON.parseObject(responseString,new TypeReference<Response<DateIsHolidayResponseData>>(){});
        return ApiGatewayResponse.createApiGatewayResponse(response);
    }


    private FunctionComputeClient getFunctionComputeClient(Context context){
        String accessKeyId = context.getExecutionCredentials().getAccessKeyId();
        String accessKeySecret = context.getExecutionCredentials().getAccessKeySecret();
        String securityToken = context.getExecutionCredentials().getSecurityToken();
        String cacheKey = accessKeyId + "@Hello@" + accessKeySecret + "@Hello@" + securityToken;
        if(clientMap.containsKey(cacheKey)){
            return clientMap.get(cacheKey);
        }

        String region = localConfig.getConfigStringValue("fc_region");
        String accountId = localConfig.getConfigStringValue("fc_accountId");
        Config config = new Config(region, accountId, accessKeyId, accessKeySecret, securityToken, false);

        FunctionComputeClient fcClient = new FunctionComputeClient(config);
        clientMap.put(cacheKey,fcClient);
        return fcClient;
    }

    private ConcurrentHashMap<String,FunctionComputeClient> clientMap = new ConcurrentHashMap<>(1);

}
