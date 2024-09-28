package org.apiautomation.ex_22092024.pojos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POJO_001 {

    //String Payload
    public static void main(String[] args) {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth").contentType(ContentType.JSON)
                .body(payload).log().all()
                .when().post()
                .then().log().all().statusCode(200);
    }

}
