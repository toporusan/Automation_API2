package Day_3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class HeadersDemo {

    //@Test(priority = 1)
    void testCookies() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")// валидация хедеров
                .and()// используется для объединения различных условий или действий в одной цепочке - не обязательно!
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws");

    }

  //  @Test(priority = 2)
    void getHeader() {

        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get single header info
        String headerValue = res.getHeader("Content-Type"); // получение значение хедера "Content-Type"
        System.out.println("The value of the Content-type is: " + headerValue);

        //get all headers info

        Headers myHeaders = res.getHeaders();
        for (Header hd : myHeaders) {
            System.out.println();
            System.out.println(hd.getName()+ "        "+hd.getValue());
        }

    }

    @Test(priority = 3)
    void getHeader2() {

        Response res = given()
                .when()
                .get("https://mobapi-dev.fincube.uz/api/card/");


        //get all headers info

        Headers myHeaders = res.getHeaders();
        for (Header hd : myHeaders) {
            System.out.println(hd.getName()+ "        "+hd.getValue());
        }

    }


}
