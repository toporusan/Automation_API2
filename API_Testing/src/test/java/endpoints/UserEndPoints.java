package endpoints;

import io.restassured.response.Response;
import payload_POJO.User;

import static endpoints.Routs.*;
import static io.restassured.RestAssured.given;


//UserEndPoints.java
// Perform to Create,Read,Update and Delete the user API
public class UserEndPoints {

    // POST
    public static Response createUSer(User payload) {
        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
                .when()
                .post(post_url);

        return response;
    }

    // GET
    public static Response readUser(String username) {
        Response response = given()
                .accept("application/json")
                .pathParam("username", username) // имя конкретного пользователя
                .when()
                .get(get_url);

        return response;
    }

    // PUT
    public static Response updateUser(User payload, String userName) {
        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .pathParam("username", userName) // имя конкретного пользователя
                .body(payload)
                .when()
                .put(put_url);

        return response;
    }

    // DELETE
    public static Response deleteUSer(User payload,String userName) {
        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .pathParam("username", userName) // имя конкретного пользователя
                .body(payload)
                .when()
                .delete(delete_url);

        return response;
    }

}
