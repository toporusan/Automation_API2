package Day_8;

import Day_7.Faker_Data_Generator;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    void deleteUser(ITestContext context) {

        String bearerToken = "abf14747f06d50e5e225ed0ceec8b4b5d9cdaeede1448100344eb3bc28347e80";
        //int id = (Integer) context.getAttribute("user_id"); // переменная видна в пределах теста
        int id = (Integer) context.getSuite().getAttribute("user_id"); // переменная видна в пределах test-suite

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParam("id", id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();

    }
}
