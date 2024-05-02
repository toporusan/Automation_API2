package ALessons.Day_4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ParsingJsonResponseDataTest {

    @Test(priority = 1)
    void testJsonResponse() {

        //Approach 1
      /*  given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/books")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("books[0].title", equalTo("1984"));*/


        //Approach 2

        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/book");

      /*  Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json");

        String title = res.jsonPath().get("[3].title").toString();// получаем title четвертого объекта
        Assert.assertEquals(title,"One Hundred Years of Solitude");
        System.out.println(title);*/


        // JSONObject Class -представляет собой контейнер для хранения и работы с данными в формате JSON

        String a = res.getBody().asPrettyString(); // получили тело (массив объектов)

        JSONArray jsonArray = new JSONArray(a); // Создали JSONArray (объект) из строки

        JSONObject jo = new JSONObject();// Создали новый JSONObject();
        jo.put("book", jsonArray); // присвоили ему объект с ключем "book" и значением jsonArray

        for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

            String booKTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            System.out.println(booKTitle);
        }

        // search for title of the book in json - validation 1
        System.out.println();
        System.out.println("Проверка по названию есть ли книга в списке:");

        boolean status = false;
        for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

            String booKTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            if (booKTitle.equals("One Hundred Years of Solitude")) {
                status = true;
                System.out.println(booKTitle);
                break;
            }
        }

        Assert.assertTrue(status,"Title isn't present: ");


        // validate total price of books - validation 2
        System.out.println();
        System.out.println("Проверка общей стоимости:");
        double totalPrice = 0;

        for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

            String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            totalPrice += Double.parseDouble(price);
        }
        System.out.println(totalPrice);

        Assert.assertEquals(totalPrice,45.96);



    }


}
