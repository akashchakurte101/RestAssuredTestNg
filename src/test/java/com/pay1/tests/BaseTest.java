package com.pay1.tests;


import com.google.common.collect.ImmutableMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.InputStream;
import java.lang.reflect.Method;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
public class BaseTest {
    public static JSONObject expectedData;
    public InputStream inputStream;
    @BeforeSuite
    public void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("Browser.Version", "70.0.3538.77")
                        .put("URL", "http://testjs.site88.net")
                        .build(), System.getProperty("user.dir")
                        + "/allure-results/");


    }
    @BeforeTest
  public void beforeTest(){
        String fileName="data/expectedData.json";
        inputStream=getClass().getClassLoader().getResourceAsStream(fileName);
        JSONTokener tokener = new JSONTokener(inputStream);
        expectedData=new JSONObject(tokener);

   }

    @BeforeMethod
   public void beforeMethod(Method method){
       System.out.println("STARTING TEST: " + method.getName());
   }
}
