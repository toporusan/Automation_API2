package Day_5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class FileUploadAndDownload {

    @Test
    void singleFileUpload() {
        File file = new File("D:\\avatar_1655396445.png");

        given()
                .multiPart("file", file)
                .contentType("multipart/form-data") // читать в конце страницы
                .when()
                .post("http://localhost:8080/upload")
                .then()
                .statusCode(200)
                .body("fileName", equalTo("avatar_1655396445.png"))
                .log().all();
    }


    @Test
    void multipleFileUpload() {
        File file1 = new File("D:\\avatar_1655396445.png");
        File file2 = new File("D:\\avatar_1655.png");

        given()
                .multiPart("files", file1)
                .multiPart("files", file2)
                .contentType("multipart/form-data") // читать в конце страницы
                .when()
                .post("http://localhost:8080/upload")
                .then()
                .statusCode(200)
                .body("[0]fileName", equalTo("avatar_1655396445.png"))
                .body("[1]fileName", equalTo("avatar_1655.png"))
                .log().all();
    }

    @Test
    void multipleFileUpload2() {
        File file1 = new File("D:\\avatar_1655396445.png");
        File file2 = new File("D:\\avatar_1655.png");

        File[] fileArray = {file1,file2}; // when we want to send a lot of files

        given()
                .multiPart("files", fileArray)
                .contentType("multipart/form-data") // читать в конце страницы
                .when()
                .post("http://localhost:8080/upload")
                .then()
                .statusCode(200)
                .body("[0]fileName", equalTo("avatar_1655396445.png"))
                .body("[1]fileName", equalTo("avatar_1655.png"))
                .log().all();
    }

    @Test
    void fileDownload() {
        File file = new File("D:\\avatar_1655396445.png");

        given()
                .when()
                .get("http://localhost:8080/downloadFile/avatar_1655396445.png")
                .then()
                .statusCode(200)
                .log().body();
    }


}


/*
                            multiPart()
Метод multiPart() используется в библиотеке REST Assured для указания передачи файла
в теле запроса HTTP в виде мультичасти.
Этот метод позволяет отправлять файлы, вместе с другими данными, в запросах типа POST или PUT.

Общий синтаксис метода multiPart() выглядит следующим образом:
.multiPart(String controlName, File file)


                        .contentType("multipart/form-data")

.contentType("multipart/form-data") - это метод, который устанавливает тип содержимого
(Content-Type) HTTP-запроса как "multipart/form-data".

                            "multipart/form-data"

"multipart/form-data" - это один из типов контента, используемых в HTTP-запросах
для отправки файлов и других бинарных данных. Он позволяет отправлять данные, включая файлы,
в теле запроса с помощью механизма мультипарт-кодирования. Это особенно полезно, когда вы хотите
 отправить файлы или другие нестандартные данные, такие как изображения или аудиофайлы, через HTTP-запрос.

При использовании типа содержимого "multipart/form-data", данные разбиваются на несколько
частей (подразделений), каждая из которых содержит отдельную порцию данных, включая файлы,
 и отправляются вместе в теле запроса. Каждая часть имеет свой уникальный идентификатор и
 может содержать дополнительные метаданные, такие как имя файла и тип содержимого.

Этот тип содержимого часто используется в HTML-формах для отправки файлов через браузер,
а также в API для работы с файлами и другими бинарными данными через HTTP-протокол.

*/