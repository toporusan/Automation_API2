package Day_7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.internal.path.ArraySliceOperation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
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

    @Test(priority = 1)
    void GET_method() {
        given()
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/students")
                .then()
                .log().body();

        System.out.println("----------------------------------------");
    }


    @Test(priority = 2)
    void PUT_method() {

        Response res = given() // Сохранили ответ на запрос
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/students");

        Faker faker = new Faker();// создали объект для генерации фейковых данных
        String fakeCourse = String.valueOf(faker.programmingLanguage().name()); // создали курс с помощью фейкера
        String fakeCourse2 = String.valueOf(faker.programmingLanguage().name());

        JSONArray array = new JSONArray(res.body().asPrettyString());
        array.getJSONObject(0).getJSONArray("courses").put(0, fakeCourse);//из первого объекта
        // взяли массив в котором взяли по индексу 0 значение и заменили его
        array.getJSONObject(0).getJSONArray("courses").put(1, fakeCourse2);

        // PUT - данным методом меняем данные о курсе студента под Id - f66f
        given()
                .contentType("application/json")
                .body(array.getJSONObject(0).toString())// Передали объект в котором поменяли курсы
                .when()
                .put("http://localhost:3000/students/f66f")
                .then()
                .log().body();

    }

    @Test(priority = 3)
    void POST_method() {
        Faker student = new Faker(); // создали объект фейкер чтобы через его методы назначать динамичные данные

        POJO_Student newStudent = new POJO_Student(); // Создали поджо класс для того чтобы генерировать запрос

        newStudent.setName(String.valueOf(student.name().firstName()));
        newStudent.setLocation(String.valueOf(student.country().name()));
        newStudent.setPhone(String.valueOf(student.phoneNumber().cellPhone()));
        ArrayList<String> course1 = new ArrayList<>();
        course1.add(String.valueOf(student.programmingLanguage().name()));
        course1.add(String.valueOf(student.programmingLanguage().name()));
        newStudent.setCourses(course1);

        ObjectMapper objectMapper = new ObjectMapper(); // import from com.fasterxml.jackson.databind.ObjectMapper;
        String jsonData;
        try {
            jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newStudent); // трансформировали POJO в строку
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


    @Test(priority = 4)
    void DELETE_method() {
        Response res = given() // Сохранили ответ на запрос
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/students");

        JSONArray array = new JSONArray(res.body().asPrettyString());
        JSONObject object = array.getJSONObject(array.length() - 1);
        String id = String.valueOf(object.get("id"));

        // DELETE - данным методом мы удаляем студента согласно заданному ID
        given()
                .contentType("application/json")
                .when()
                .delete("http://localhost:3000/students/" + id)
                .then()
                .assertThat()
                .statusCode(200).log().status();


        given() // Провели проверку есть ли удаленный объект в новом списке
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/students/" + id)
                .then().assertThat().statusCode(404).log().status();


    }
}




