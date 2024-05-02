package ALessons.Day_1;

/*  Create Tests in Rest Assured

given()
in this  section we end content type,set cookies,add auth, add param,set headers info ect... (pre-request)

when()
In thi section we specified request types: get, post, put, delete   (request types)

then()
Validation :  validate stus code, extract response, extract headers, cookies & response body... (Validation)

*/


import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequests {

    int id;

    @Test(priority = 1)
    void getUsers() {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();

        System.out.println();
        System.out.println("******************************");

    }

    @Test(priority = 2)
    void createUser() {
        HashMap data = new HashMap<>();
        data.put("name", "Vasif");
        data.put("job", "QA");
        String contentType = "application/json";

        id = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all();


        System.out.println();
        System.out.println("******************************");

    }

    @Test(priority = 3, dependsOnMethods = ("createUser"))
    void updateUser() {

        HashMap data = new HashMap<>();
        data.put("name", "Bob");
        data.put("job", "Engineer");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200)
                .log().all();

        System.out.println();
        System.out.println("******************************");

    }


    @Test(priority = 4)
    void deleteUser() {

        given()
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204)
                .log().all();

    }



}
