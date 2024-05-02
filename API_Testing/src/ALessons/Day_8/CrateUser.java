package ALessons.Day_8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CrateUser {

    @Test
    void createUser(ITestContext context) {

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

        //context.setAttribute("user_id",id); // create global variable - работает в пределах одного теста
        // данная переменная доступна только в пределах одного теста, не доступна в других тестах

        context.getSuite().setAttribute("user_id",id);// в таком контексте данная переменная доступна на уровне
        //test-suite  если добавить метод getSuite()
    }
}

