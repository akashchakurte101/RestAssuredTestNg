package com.pay1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay1.api.StatusCode;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonMethods {
    public static  String Jsondata(Object object) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String jacksonData = objectMapper.writeValueAsString(object);
        return jacksonData;
    }
    @Step
    public static void assertStatusCode(int actualStatusCode, StatusCode statuscode){
        assertThat(actualStatusCode,equalTo(statuscode.code));
    }
    @Step
    public static void assertDescription(Response response, String expectedString){
        JsonPath jsonPath=response.jsonPath();
        assertThat(jsonPath.getString("description"), equalTo(expectedString));
    }
}
