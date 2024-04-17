package Day_6;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONSchemaValidation {

    @Test
    void jsonschemavalidation() {

        given()
                .when()
                .get("http://localhost:3000/book/")
                .then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema_book.json"));

    }


}
