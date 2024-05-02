package ALessons.Day_3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesDemo {

    //@Test(priority = 1)
    void testCookies() {
        given()
                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC", "Ae3NU9MC67EC937Yv7v9amkIB6OpgVDM1p8OiH7xfVbIW50V69cPNqFWXg")
                .log().all();

    }

    @Test(priority = 2)
    void GetCookiesInfo() {

        Response res = given() // Переменная restAssured которая сохраняет "ответ" на запрос get
                .when()
                .get("https://www.google.com/");

        //get single cookie info
        //String cookie_value = res.getCookie("AEC");
        //System.out.println("Value of cookie is =====> " + cookie_value);


        //get all cookie info
        Map<String, String> cookie_values = res.getCookies(); // getCookies() извлекает все куки из ответа
        for ( String k: cookie_values.keySet()) {

            String cookie_value = res.getCookie(k);// getCookie() извлекает куки по ключу "k"
            System.out.println(k +": "+cookie_value);

        }


    }

}

