package org.apiautomation.ex_22092024.jsonpath;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONPath01 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void test_post() {

        String dynamic_value = "Brown";

        Faker faker = new Faker();
        String dynamic_fake_firstname = faker.name().firstName();

        System.out.println(dynamic_fake_firstname);

        String payload = "{\n" +
                "    \"firstname\" : \"" + dynamic_fake_firstname + "\",\n" +
                "    \"lastname\" : \"" + dynamic_value + "\",\n" +
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
        requestSpecification.body(payload).log().all();

        response = requestSpecification.when().post();

        System.out.println(response.asString());

        JsonPath jsonPath = new JsonPath(response.asString());
        String bookingId = jsonPath.getString("bookingid");
        String firstname = jsonPath.getString("booking.firstname");
        String checkout = jsonPath.getString("booking.bookingdates.checkout");

        System.out.println(bookingId);
        System.out.println(firstname);
        System.out.println(checkout);

        //assertj
        assertThat(bookingId).isNotNull().isNotBlank().isGreaterThan("0");
        assertThat(firstname).isEqualTo("Jim").isNotNull().isNotBlank();
        assertThat(checkout).isNotNull().isNotBlank();


        //TestNG
        Assert.assertEquals(firstname, "Jim");

    }

}
