package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().when().get("/stores").then().statusCode(200);
    }

    //1. Verify the if the total is equal to 1561
    @Test
    public void verifyTotal() {
        response.body("total", equalTo(1561));
    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void veryLimit() {
        response.body("limit", equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void verifySingleName() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void verifyMultipleNames() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    //5. Verify the storied=7 inside store services of the third store of second services
    @Test
    public void verifyStoreId() {
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));
    }

    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void verifyCreatedAt() {
        String createdAt = response.extract().path("data[2].services[4].storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Create At :" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Verify the state = MN of forth store
    @Test
    public void verifyState() {
        String state = response.extract().path("data[3].state");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("State :" + state);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Verify the store name = Rochester of 9th store
    @Test
    public void verifyStoreName() {
        String storeName = response.extract().path("data[8].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("StoreName :  " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //9.Verify the storeId = 11 for the 6th store
    @Test
    public void verifyStoreID() {
        int storeId = response.extract().path("data[5].id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("StoreId : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyServiceID() {
        int Serviced = response.extract().path("data[6].services[3].id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("ServiceID : " + Serviced);
        System.out.println("------------------End of Test---------------------------");
    }
}
