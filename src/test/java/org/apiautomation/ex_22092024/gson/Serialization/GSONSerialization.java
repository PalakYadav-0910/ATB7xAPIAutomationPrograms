package org.apiautomation.ex_22092024.gson.Serialization;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GSONSerialization {

    // Strings
    // HashMap
    // Class - POJO ->Plain Old Java Object
    // Create class for the Payload - POJO

    //PUT Request
    //token, booking id

    //Payload

    /*
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
   }
*/

    //Rules to Convert above payload to Class(POJO)
    // 1. {} -> class, keywords -> are names of data types
    // 2. {} -> another class - keys are nothing. instance variable.


    RequestSpecification r = RestAssured.given();

    Response response;

    ValidatableResponse validatableResponse;

    @Description("TC#1 - Verify that create booking is working with valid payload")
    @Test
    public void testNonBDDStylePOSTPositive(){

        Booking booking = new Booking();
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");

        booking.setBookingDates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);

        //Java Object (booking) -> JSON String (byteStream) -> Serialization
        Gson gson = new Gson();
        String jsonStringPayload = gson.toJson(booking);
        System.out.println(jsonStringPayload);

        String BASE_URL = "https://restful-booker.herokuapp.com";
        String BASE_PATH = "/booking";

        r.baseUri(BASE_PATH);
        r.baseUri(BASE_URL);
        r.contentType(ContentType.JSON);
        r.body(jsonStringPayload).log().all();

        response = r.when().log().all().post();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Parse - DeSerialization
        /*

        * Note:-
        * When we deserialize, we should check response, as response doesn't
        * have same fields now, one more field bookingId is added which is
        * not a part of the POJO Class
        * So we will need to add one more class for Response for Deserialization.
        *
        * */



    }

}
