package org.apiautomation.ex_21092024.testngexamples.CRUD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCaseIntegration {

    // Create a Token
    // Create a Booking
    // Perform a PUT Request
    // Verify that PUT is success by GET Request
    // Delete the ID
    // Verify it doesn't exist GET Request

    String token;
    String bookingID;

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;


    public String getToken(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        token = response.jsonPath().getString("token");
        System.out.println(token);

        return token;

    }


    public String getBookingID(){

        String payload_POST = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST).log().all();

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingID = response.jsonPath().getString("bookingid");
        System.out.println(bookingID);

        return bookingID;

    }

    @Test(priority = 1)
    public void test_update_request_put(){

        String payload_PUT = "{\n" +
                "    \"firstname\" : \"Palak\",\n" +
                "    \"lastname\" : \"Yadav\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        token = getToken();
        bookingID = getBookingID();

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payload_PUT).log().all();

        response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test(priority = 2)
    public void test_update_request_get(){

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/booking");
        requestSpecification.basePath("/" + bookingID).log().all();

        response = requestSpecification.when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Assert.assertEquals(response.then().extract().path("firstname"), "Palak");
        Assert.assertEquals(response.then().extract().path("lastname"), "Yadav");

    }

    @Test(priority = 3)
    public void test_delete_booking(){

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/booking");
        requestSpecification.basePath("/" + bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token).log().all();

        response = requestSpecification.when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }

    @Test(priority = 4)
    public void test_after_delete_request_get(){

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/booking");
        requestSpecification.basePath("/" + bookingID).log().all();

        response = requestSpecification.when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

        Assert.assertEquals(response.then().extract().statusCode(), 404);

    }

}
