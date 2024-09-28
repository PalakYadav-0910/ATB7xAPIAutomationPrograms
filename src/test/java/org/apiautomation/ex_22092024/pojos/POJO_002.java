package org.apiautomation.ex_22092024.pojos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class POJO_002 {

    static RequestSpecification requestSpecification;
    static Response response;
    static ValidatableResponse validatableResponse;

    //HashMap Payload
    //Note:-
    /*
    * By default, RestAssured body(payload) method doesn't support HashMap.
    * It supports string inouts, file inputs.
    * So we need to convert HashMap to InputStream with the help of 2 libraries.
    *
    * Map->String->Known as Serialization->GSON/Jackson API
    * String->Java/ByteStream/HashMap->Known as DeSerialization->GSON/Jackson API
    *
    * 1. GSON
    * <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
      <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.11.0</version>
      </dependency>

    *   (or)
    *
    * 2. Jackson API
    *
    *<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
     <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.18.0</version>
    </dependency>

    *
    * */

    @Test
    public static void test_post_with_hashmap() {

        /*Payload
        {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" :
            {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }*/

        // HashMap ->
        // {} - map-> In this there are two maps, 1 is parent map, other is child map

        //Parent Map
        HashMap<String, Object> jsonBodyUsingMap = new LinkedHashMap<>();
        jsonBodyUsingMap.put("firstname", "Jim");
        jsonBodyUsingMap.put("lastname", "Brown");
        jsonBodyUsingMap.put("totalprice", 111);
        jsonBodyUsingMap.put("depositpaid", true);

        //Child Map
        HashMap<String, Object> bookingDatesMap = new LinkedHashMap<>();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");

        //Adding bookingdates field which itself is another json/map
        jsonBodyUsingMap.put("bookingdates", bookingDatesMap);

        jsonBodyUsingMap.put("additionalneeds", "Breakfast");

        System.out.println(jsonBodyUsingMap);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonBodyUsingMap).log().all();

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        String bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);

    }

}
