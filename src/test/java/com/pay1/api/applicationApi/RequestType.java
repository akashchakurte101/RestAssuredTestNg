package com.pay1.api.applicationApi;

import com.pay1.api.RestResource;
import io.restassured.response.Response;

public class RequestType {
    public static Response post(String endPoint ,String body,String Environment) throws Exception {
        return RestResource.post(endPoint,body,Environment);
    }

    public static Response get(String endPoint, String Environment){
        return RestResource.get(endPoint, Environment);
    }
}
