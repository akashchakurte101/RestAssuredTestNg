package com.pay1.tests;

import com.pay1.api.EndPoints;
import com.pay1.api.StatusCode;
import com.pay1.api.applicationApi.RequestType;
import com.pay1.utils.CommonMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentGatewayTests {
    @Test
    @Parameters("Environment")
    public void checkBankList(String Environment){
        Response response =RequestType.get(EndPoints.pgEndPoint,Environment);
        CommonMethods.assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        JsonPath jsonpath=response.jsonPath();
        HashMap<Object, Object> map=jsonpath.getJsonObject("data");
        List<Object> listOfBanks=(List<Object>) map.get("banks");
        int size=listOfBanks.size();
        System.out.println("Total Number of banks:-"+size);
        Assert.assertEquals(size,BaseTest.expectedData.get("Bank Size"));
//		Map<String,Object> onelist=(Map<String,Object>)listOfBanks.get(0);
//		System.out.println(onelist);
        int i=0;
        while(i<size) {
            Map<String,Object> onelist=(Map<String, Object>)listOfBanks.get(i);
            Object o=onelist.get("name");
            System.out.println(o);
            i++;
        }

        Assert.assertEquals(size,BaseTest.expectedData.get("Bank Size"));
    }
}
