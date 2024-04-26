package Day_8;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class CrateUser {

    @Test
    void test_createUser(ITestContext context) {

        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        String bearerToken = "abf14747f06d50e5e225ed0ceec8b4b5d9cdaeede1448100344eb3bc28347e80";

        int id = given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println("ID: " + id);

        context.setAttribute("user_id",id); // create global variable
    }
}

