package Day_3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class LoggingDemoTest {

    @Test(priority = 1)
    void testLogs() {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().cookies();
    }
}
