package endpoints;

import io.restassured.response.Response;
import payload_POJO.User;
import java.util.ResourceBundle;
import static io.restassured.RestAssured.given;


//UserEndPoints.java
// Perform to Create,Read,Update and Delete the user API
public class UserEndPoints2 {


    // method created for getting URL's from property files
    public static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes");// Load properties bundle
        return routes;
    }


    // POST
    public static Response createUSer(User payload) {
       String post_url = getURL().getString("post_url");

        Response response = given()
                .accept("application/json")
                .contentType("application/json")
                .body(payload)
                .when()
                .post(post_url);

        return response;
    }

    // GET
    public static Response readUser(String userName) {
        String get_url = getURL().getString("get_url");

        Response response = given()
                .accept("application/json")
                .pathParam("username", userName) // имя конкретного пользователя
                .when()
                .get(get_url);

        return response;
    }

    // PUT
    public static Response updateUser(User payload, String userName) {
        String put_url = getURL().getString("put_url");

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
    public static Response deleteUser(String userName) {
        String delete_url = getURL().getString("delete_url");

        Response response = given()
                .pathParam("username", userName) // query - имя конкретного пользователя
                .when()
                .delete(delete_url);

        return response;
    }

}
