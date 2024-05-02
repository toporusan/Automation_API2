package ALessons.Day_2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Ways_to_create_Post_Request_body {

    String id;
    // 1) Post request body using HashMap

    //@Test(priority = 1)
    void testPostusingHashMap() {

        HashMap data = new HashMap();
        data.put("name", "Vasif");
        data.put("location", "Tashkent");
        data.put("phone", "1236456");

        String courseArr[] = {"C", "C++"};
        data.put("courses", courseArr);


        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .body("name", equalTo("Vasif"))
                .body("location", equalTo("Tashkent"))
                .body("phone", equalTo("1236456"))
                .header("Content-type", "application/json")
                .log().all();

    }



    // 2) Post request body using org.json library
    //@Test(priority = 1)
    void testPostusingJsonLibrary() {

        JSONObject data = new JSONObject();

        data.put("name", "Scott");
        data.put("location", "London");
        data.put("phone", "1231156");

        String courseArr[] = {"Java", "C#"};
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data.toString())// data was changed to String
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("C#"))
                .body("name", equalTo("Scott"))
                .body("location", equalTo("London"))
                .body("phone", equalTo("1231156"))
                .header("Content-type", "application/json")
                .log().all();

    }




    // 3) Post request body using POJO Class
    @Test(priority = 1)
    void testPostusingPOJO() {

        POJO_Student data = new POJO_Student();
        data.setName("Marry");
        data.setLocation("Hamburg");
        data.setPhone("55565666");
        String courseArr [] = {"Java", "Go"};
        data.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Go"))
                .body("name", equalTo("Marry"))
                .body("location", equalTo("Hamburg"))
                .body("phone", equalTo("55565666"))
                .header("Content-type", "application/json")
                .log().all();

    }



    // 4) Post request body using External Json File
    @Test(priority = 1)
    void testPostusingExternalJsonFile() {

       File f = new File("src/test/java/Lessons/Day_2/body.json");

        try( FileReader fr = new FileReader(f) ) {

            JSONTokener jt = new JSONTokener(fr);
            JSONObject data = new JSONObject(jt);

            given()
                    .contentType("application/json")
                    .body(data.toString())// data was changed to String
                    .when()
                    .post("http://localhost:3000/students")
                    .then()
                    .statusCode(201)
                    .body("courses[0]", equalTo("Java"))
                    .body("courses[1]", equalTo("Go"))
                    .body("name", equalTo("Harry"))
                    .body("location", equalTo("Nevada"))
                    .body("phone", equalTo("8799655566"))
                    .header("Content-type", "application/json")
                    .log().all();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }






    // Deleting student record
    @Test(priority = 2)
    void testDelete() {
        id = given()
                .contentType("application/json")
                .when()
                .post("http://localhost:3000/students")
                .jsonPath().getString("id");

        System.out.println("***** " +id);

        given()
                .when()
                .delete("http://localhost:3000/students/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }
}
