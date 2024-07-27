package com.pay1.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay1.api.EndPoints;
import com.pay1.api.StatusCode;
import com.pay1.api.applicationApi.RequestType;
import com.pay1.pojo.LoginParams;
import com.pay1.utils.CommonMethods;
import com.pay1.utils.DataLoder;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends BaseTest {
    @Test
    @Parameters("Environment")
    public void successfullLogin(String Environment) throws Exception {
        String projectPath=System.getProperty("user.dir");
        LoginParams loginReuest=LoginBuilder(projectPath+"\\src\\test\\resources\\ApiData.xlsx","Login_Api","SuccessLogin");
        String body= CommonMethods.Jsondata(loginReuest);
        Response response = RequestType.post(EndPoints.platformEndPoint,body,Environment);
        CommonMethods.assertStatusCode(response.statusCode(),StatusCode.CODE_200);
        CommonMethods.assertDescription(response,BaseTest.expectedData.getJSONObject("platform").getJSONObject("login").getString("valid Login description"));
    }

    public LoginParams LoginBuilder(String fileName, String sheetName, String Testcases) throws IOException {
        ArrayList<String> ExcelData = DataLoder.Read_Excel_Data(fileName, sheetName, Testcases);
        return LoginParams.builder().method(ExcelData.get(1))
                .app_name(ExcelData.get(2)).
                app_type(ExcelData.get(3)).
                app_version(ExcelData.get(4)).
                version_code(ExcelData.get(5)).
                mobile(ExcelData.get(6)).
                password(ExcelData.get(7)).
                uuid(ExcelData.get(8)).build();
    }
}
