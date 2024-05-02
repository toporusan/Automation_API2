package ALessons.Day_8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void updateUser(ITestContext context) {
        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        String bearerToken = "abf14747f06d50e5e225ed0ceec8b4b5d9cdaeede1448100344eb3bc28347e80";
        //int id = (Integer) context.getAttribute("user_id"); // переменная видна в пределах теста
        int id = (Integer) context.getSuite().getAttribute("user_id"); // переменная видна в пределах test-suite


        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .pathParam("id", id)
                .body(data.toString())
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .log().all();


    }
}
