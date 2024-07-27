package com.pay1.api;

import com.pay1.encryption.AESCrypt;
import io.restassured.response.Response;

import static com.pay1.api.SpecBuilder.*;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String body,String environment) throws Exception {
        AESCrypt encryptData = new AESCrypt(environment);
        String req=encryptData.encrypt(body);

        return given(getRequestSpec(environment)).
                    param("req",req).
                when().
                    post(path).
                then().
                    spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String endPoint, String environment){
      return given(getRequestSpec(environment)).
              when().
                  get(endPoint).
              then().
                   spec(getResponseSpec()).
                  extract().
                  response();
    }


}
