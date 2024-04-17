package Day_7;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Faker_Data_Generator {

   // @Test (priority = 1)
    void testGenerateDummyData() {

        Faker faker = new Faker();

        // Create name and lastname
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        System.out.println("Full Name: " + fullName);
        System.out.println("Name: " + firstName);
        System.out.println("Last name: " + lastName);

        System.out.println();

        //create username and password

        String userName = faker.name().username();
        String password = faker.internet().password();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String email = faker.internet().safeEmailAddress();

        System.out.println("UserName: " + userName);
        System.out.println("Password: " + password);
        System.out.println("Phone number: " +phoneNumber);
        System.out.println("Email: " +email);

    }


    @Test (priority = 2)
    void add_JSON_Data() {
        System.out.println();

        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/students");

        String json = res.body().asPrettyString();
        System.out.println(json);

        JSONArray array = new JSONArray(json);
        String course = String.valueOf(array.getJSONObject(0).getJSONArray("courses").get(0));
        System.out.println(course);

        array.getJSONObject(0).getJSONArray("courses").put(0, "Selenide");

        // System.out.println(String.valueOf(array));

        /*Response res2 = given()
                .contentType("application/json")
                .body(array)
                .when()
                .put("http://localhost:3000/students/f66f");*/
    }
}
