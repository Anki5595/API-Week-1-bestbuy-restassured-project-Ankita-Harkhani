package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
1. Extract the limit
2. Extract the total
3. Extract the name of 5th store
4. Extract the names of all the store
5. Extract the storeId of all the store
6. Print the size of the data list
7. Get all the value of the store where store name = St Cloud
8. Get the address of the store where store name = Rochester
9. Get all the services of 8th store
10. Get storeservices of the store where service name = Windows Store
11. Get all the storeId of all the store
12. Get id of all the store
13. Find the store names Where state = ND
14. Find the Total number of services for the store where store name = Rochester
15. Find the createdAt for all services whose name = “Windows Store”
16. Find the name of all services Where store name = “Fargo”
17. Find the zip of all the store
18. Find the zip of store name = Roseville
19. Find the storeservices details of the service name = Magnolia Home Theater
20. Find the lat of all the stores
*/
public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void setUp() {
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void ExtractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void getNameOf5thStore() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void getNameOfAllStore() {
        List<String> listOfStoreName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all stores are : " + listOfStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void getListOfIds() {
        List<Integer> lisOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + lisOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void getSizeOfList() {
        List<String> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the size of the data list is : " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void getValueOfStore() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for store name 'St Cloud' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void getAddressOfStore() {
        List<String> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of 'Rochester' is: " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void getServicesOfStore() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for 8th store are: " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void getStoreServices() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store services for store name 'Windows Store' are: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void getStoreId() {
        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store ids of the store data is : " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void getId() {
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the data is : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void getStoreWithStateND() {
        List<String> state = response.extract().path("data.findAll{it.state =='ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The state for store is: " + state);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void totalNumberOfServices() {
        List<Integer> numberOfServices = response.extract().path("data.findAll{it.name =='Rochester'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for store name 'Rochester' are: " + numberOfServices.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void allServicesForWindowStore() {
        List<HashMap<String, ?>> createdATOfServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for store name 'Windows Store' are: " + createdATOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void allServicesForFargo() {
        List<HashMap<String, ?>> allServices = response.extract().path("data.findAll{it.name =='Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for store name 'Fargo' are: " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void findZipForAllTheStore() {
    List<String> zipOfTheStore = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all stores are : " + zipOfTheStore);
        System.out.println("------------------End of Test---------------------------");
}

    //18. Find the zip of store name = Roseville
    @Test
    public void findZipForRosevilleStore() {
        List<Integer> zip = response.extract().path("data.findAll{it.name =='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for store name 'Fargo' are: " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void allServicesForMagnoliaHomeTheater() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for store name 'Magnolia Home Theater' are: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void latForAllTheStore() {
        List<Double> latOfTheStore = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all stores are : " + latOfTheStore);
        System.out.println("------------------End of Test---------------------------");
    }
}