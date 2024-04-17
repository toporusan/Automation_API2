package Day_7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {
  //@Test(priority = 1)
    void testBasicAuthentication() {

        given()
                .auth().basic("postman", "password") // отправляет свои учетные данные (обычно имя пользователя
                .when()                                      //и пароль) в заголовке запроса
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true)) // { "authenticated": true }
                .log().all();


    }


   // @Test(priority = 2)
    void testDigestAuthentication() {

        given()
                .auth().digest("postman", "password") //отправляет хэш своих учетных данных вместо самих учетных данных
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true)) // { "authenticated": true }
                .log().all();

    }


  //  @Test(priority = 3)
    void testPreemptiveAuthentication() {

        given()
                .auth().preemptive().basic("postman", "password") //это метод аутентификации, при котором клиент отправляет
                .when()                                         // учетные данные серверу до того, как сервер запрашивает их.
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true)) // { "authenticated": true }
                .log().all();

    }


    //@Test(priority = 4)
    void testBearerTokenAuthentication() {

        String bearerToken = "";


        given()
                .header("Authorization", "Bearer " + bearerToken) // F4ZNoLLrbtZ
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();

    }

   // @Test(priority = 5)
    void testOAuth1Authentication() {

        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessesToken", "TokenSecret") // this is OAuth1.0 auth
                .when()
                .get("URL")
                .then()
                .statusCode(200)
                .log().all();

    }

  // @Test(priority = 6)
    void testOAuth2Authentication() {

        String bearerToken = "";

        given()
                .auth().oauth2(bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(priority = 7)
    void testAPIKey2Authentication() {

        String weatherAPIky = "2";

        // Method 1
        given()
                .queryParam("appid", "") // appid is APIKey
                .when()
                .get("http://api.openweathermap.org/data/2.5/air_pollution?lat=51.5073219&lon=-0.1276474")
                .then()
                .statusCode(200)
                .log().all();

        // Method 2
        given()
                .queryParam("appid", "") // appid is APIKey
                .pathParams("mypath", "data/2.5/air_pollution")
                .queryParam("lat",41.3082)
                .queryParam("lon",69.2598)
                .when()
                .get("http://api.openweathermap.org/{mypath}")
                .then()
                .statusCode(200)
                .log().all();

    }

}
