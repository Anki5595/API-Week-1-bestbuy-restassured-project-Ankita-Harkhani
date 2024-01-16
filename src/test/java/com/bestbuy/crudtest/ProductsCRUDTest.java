package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ProductsCRUDTest extends TestBase {

    static String name = TestUtils.getRandomValue() + "Duracell";
    static String type = TestUtils.getRandomValue() + "Housewares";
    static double price = 5.60;
    static String upc = TestUtils.getRandomValue();
    static int shipping = Integer.parseInt(TestUtils.getRandomValue());
    static String description = TestUtils.getRandomValue();
    static String manufacturer = TestUtils.getRandomValue();
    static String model = TestUtils.getRandomValue() + "MN1400R4Z";
    static String url = "http://" + TestUtils.getRandomValue();
    static String image = "http://" + TestUtils.getRandomValue();
    static int productId;

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)// replace payload to student pojo here
                .post("/products");
        productId = response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id no is :" + productId);
        response.prettyPrint();
    }
    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/products" + "/" + productId);
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void test003(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch("/products" + "/" + productId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
    @Test
    public void test004() {
        Response response = given()
                .when()
                .delete("/products" + "/" + productId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

}