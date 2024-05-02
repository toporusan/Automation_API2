package ALessons.Day_3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParameters {

    // https://reqres.in/api/users?page=2&id=5
    @Test
    void testQueryAndPathParameters() {

        given()
                .pathParams("mypath", "users") // path parameters
                 .queryParam("page", 2)// query parameters
                .queryParam("id", 5)// query parameters
                .when()
                .get("https://reqres.in/api/{mypath}")// we refer only path parameters .queryParam refer automatically
                .then()
                .statusCode(200)
                .log().all();
    }
}
