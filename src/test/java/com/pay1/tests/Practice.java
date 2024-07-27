package com.pay1.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Practice {
    public static void main(String[] args) {
        Response response =given().
                baseUri("https://reqres.in/api").
        when().
                get("/users?page=2").
        then().
                extract().
                response();
       // System.out.println(response.asString());
        JsonPath jsonPath=response.jsonPath();
        List <Map> list=jsonPath.getList("data");
        Map<String,Object> map=list.get(0);
        System.out.println(map.get("id"));
        Assert.assertEquals(map.get("id"),7);
    }
}
