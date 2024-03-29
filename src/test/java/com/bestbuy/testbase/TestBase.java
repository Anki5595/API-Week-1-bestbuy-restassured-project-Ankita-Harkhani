package com.bestbuy.testbase;

import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.port = Integer.parseInt(PropertyReader.getInstance().getProperty("port"));
    }
}
