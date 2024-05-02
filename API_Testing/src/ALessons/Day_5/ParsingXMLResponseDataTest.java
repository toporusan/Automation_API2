package ALessons.Day_5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class ParsingXMLResponseDataTest {

    @Test
    void testXMLResponse() {
        //Approach 1

        /*given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then()
                .statusCode(200)
                .header("Content-type", "application/xml")
                .body("TravelerinformationResponse.page", equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Vijay Bharath Reddy"));*/


        //Approach 2

        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-type"), "application/xml");

        String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString(); // .xmlPath().get
        Assert.assertEquals(pageNo, "1");

        String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(travelerName, "George Orwell");


    }


    @Test
    void testXMLResponseBody() {

        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");


        //XmlPath - это класс, который позволяет выполнить запросы к XML-документам,
        // используя XPath, для извлечения конкретных элементов или данных из XML-структуры.
        XmlPath xmlObj = new XmlPath(res.asString()); // принимает String

        //Verify total numbers of travelers
        List<String> travelers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travelers.size(), 10);

        //Verify traveler name is present in response
        List<String> names = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        boolean status = false;
        for (String travelerName : names) {
            if (travelerName.equals("Vijay Bharath Reddy")) {
                status = true;
                break;
            }
        }
        Assert.assertEquals(status,true);
    }


}

/*Конечная цель toString() и asString() заключается в преобразовании объекта в строку.

toString() является стандартным методом, доступным во многих языках программирования,
который обычно переопределяется в пользовательских классах для возвращения информативного
текстового представления объекта. В Java, например, toString() используется для преобразования
 объекта в строку и вызывается автоматически, когда объект используется в контексте,
 где требуется строковое представление, например, при выводе на консоль.

asString() чаще всего связан с конкретными библиотеками или фреймворками, где это метод,
предназначенный для преобразования объекта в строку с учетом специфических контекстуальных
требований или форматов данных. Например, в библиотеке для работы с сетевыми запросами, этот
метод может использоваться для преобразования тела ответа сервера в строку для дальнейшей обработки.

Таким образом, toString() более универсальный и широко используемый, в то время как asString()
может быть специфичным для определенных библиотек или операций.

*/