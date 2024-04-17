package FinCube;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Verification {

    String phoneNum = "{\n" +
            "    \"data\": {\n" +
            "        \"phone\": \"998909838035\"\n" +
            "    }\n" +
            "}";

    @Test
    void otp_phone_verify() {

         given()
                .when()
                .post("https://mobapi.apexbank.uz/api/otp/verify")
                .then()
                .statusCode(200)
                .header("Content-type", "application/xml")
                .body("TravelerinformationResponse.page", equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Vijay Bharath Reddy"));
    }

}
