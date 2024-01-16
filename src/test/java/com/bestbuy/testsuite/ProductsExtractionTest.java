package com.bestbuy.testsuite;

/*
21. Extract the limit
22. Extract the total
23. Extract the name of 5th product
24. Extract the names of all the products
25. Extract the productId of all the products
26. Print the size of the data list
27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
Pack)
28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
Pack)
29. Get all the categories of 8th products
30. Get categories of the store where product id = 150115
31. Get all the descriptions of all the products
32. Get id of all the all categories of all the products
33. Find the product names Where type = HardGood
34. Find the Total number of categories for the product where product name = Duracell - AA
1.5V CopperTop Batteries (4-Pack)
35. Find the createdAt for all products whose price < 5.49
36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
Pack)”
37. Find the manufacturer of all the products
38. Find the imge of products whose manufacturer is = Energizer
39. Find the createdAt for all categories products whose price > 5.99
40. Find the uri of all the products
*/

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void setUp() {
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void ExtractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void getNameOf5thProduct() {
        String productName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void getNameOfAllProducts() {
        List<String> listOfProductsName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all products are : " + listOfProductsName);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void getListOfIds() {
        List<Integer> lisOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + lisOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void getSizeOfList() {
        List<String> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the size of the data list is : " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void getValueOfProduct() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void getModelOfProduct() {
        List<String> model = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of 'Energizer - N Cell E90 Batteries (2-Pack)' is: " + model);
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th products
    @Test
    public void getCategoriesOfProducts() {
        List<HashMap<String, ?>> category = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories for 8th product are: " + category);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void getCategoryOfStore() {
        List<HashMap<String, ?>> categoryOfStore = response.extract().path("data.findAll{it.id =='150115'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The category for store where product id = '150115' is: " + categoryOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void grtDescriptionOfProducts() {
        List<String> descriptionOfProducts = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of the products are : " + descriptionOfProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void grtIdOfProducts() {
        List<String> idOfProducts = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of the products are : " + idOfProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void getProductsWithTypeHardGood() {
        List<String> productName = response.extract().path("data.findAll{it.type =='HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product name for HarGood is: " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void totalNumberOfCategories() {
        List<Integer> numberOfCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories for store name 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)' are: " + numberOfCategories.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void findCreatedAtForProducts() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of createdAt for all products whose price < 5.49 are: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test012() {
        List<Integer> categoriesOfProducts = response.extract().path("data.findAll{it.name =='Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of product whose name 'Energizer - MAX Batteries AA (4-Pack)' is : " + categoriesOfProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void findZipForAllTheStore() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all products are : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void findImageForProducts() {
        List<HashMap<String, ?>> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products for manufacturer is 'Energizer' are: " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void findCreatedAtForAllProducts() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of createdAt for all products whose price > 5.99 are: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the product
    @Test
    public void findURIForAllTheProducts() {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The url of all products are : " + url);
        System.out.println("------------------End of Test---------------------------");
    }
}