package org.apiautomation.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NonBDDStyle {

    static RequestSpecification r;

    public static void main(String[] args) {

        //RestAssured provide us lot of classes
        r = RestAssured.given();
        // r.useRelaxedHTTPSValidation("TLS");  -HTTPs related issues
        r.baseUri("https://zippopotam.us");

        testNonBdd_1();
        testNonBdd_2();

    }

    private static void testNonBdd_1() {

        r.basePath("/IN/560037");
        r.when().get();
        r.then().log().all().statusCode(200);

    }

    private static void testNonBdd_2() {

        r.basePath("/IN/-1");
        r.when().get();
        r.then().log().all().statusCode(404);
    }

}
