package Day_7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.internal.path.ArraySliceOperation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("Email: " + email);

    }


    @Test(priority = 2)
    void add_JSON_Data() {
        System.out.println();

        Response res = given() // Сохранили ответ на запрос
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/students");


        //String json = res.body().asPrettyString(); //
        // System.out.println(json);

        JSONArray array = new JSONArray(res.body().asPrettyString());
        String course = String.valueOf(array.getJSONObject(0).getJSONArray("courses").get(0));
        System.out.println(course);

        array.getJSONObject(0).getJSONArray("courses").put(0, "Sel----");

        System.out.println(String.valueOf(array.getJSONObject(0)));
        String put = array.getJSONObject(0).toString();
        System.out.println(put);

        // PUT - данным методом меняем данные о студенте под Id - f66f
        given()
                .contentType("application/json")
                .body(put)
                .when()
                .put("http://localhost:3000/students/f66f")
                .then()
                .log().body();


        Faker student = new Faker(); // создали объект фейкер чтобы через его методы назначать динамичные данные

        POJO_Student newStudent = new POJO_Student();
        newStudent.setName(String.valueOf(student.name().firstName()));
        newStudent.setLocation(String.valueOf(student.country().name()));
        newStudent.setPhone(String.valueOf(student.phoneNumber().cellPhone()));
        ArrayList<String> course1 = new ArrayList<>();
        course1.add(String.valueOf(student.programmingLanguage().name()));
        course1.add(String.valueOf(student.programmingLanguage().name()));
        newStudent.setCourses(course1);

        ObjectMapper objectMapper = new ObjectMapper(); // import from com.fasterxml.jackson.databind.ObjectMapper;
        String jsonData = null;
        try {
            jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newStudent);
            System.out.println(jsonData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        // POST - данным методом мы создаем новый объект на сервере (Новый студент)
        given()
                .contentType("application/json")
                .body(jsonData)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .log().body();
    }
}




