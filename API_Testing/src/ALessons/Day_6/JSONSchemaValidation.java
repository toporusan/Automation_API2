package ALessons.Day_6;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JSONSchemaValidation {

    @Test
    void jsonschemavalidation() {

        given()
                .when()
                .get("http://localhost:3000/book/")
                .then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema_book.json"));

    }


}
