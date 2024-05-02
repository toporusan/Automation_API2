package ALessons.Day_6;

import Day_2.POJO_Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class SerializationDeserialization {

    // POJO -------Serialization --->  JSON Object -- de-serialization ---> POJO


    // POJO ---> JSON
    @Test (priority = 1)
    void convertPojo2JSON() throws JsonProcessingException {

        // Created Java object using POJO class
        POJO_Student pojoStudent = new POJO_Student(); // Pojo

        pojoStudent.setName("Marry");
        pojoStudent.setLocation("Hamburg");
        pojoStudent.setPhone("55565666");
        String courseArr[] = {"Java", "Go"};
        pojoStudent.setCourses(courseArr);


        //   (serialization) convert java object ---> json object
        ObjectMapper objectMapper = new ObjectMapper(); // import from com.fasterxml.jackson.databind.ObjectMapper;

        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoStudent);

        System.out.println(jsonData);
    }


    // JSON ---> POJO
    @Test (priority = 2)
    void convertJSON2Pojo() throws JsonProcessingException {

        // Created json
        String jsonData = "{\n" +
                "  \"name\" : \"Marry\",\n" +
                "  \"location\" : \"Hamburg\",\n" +
                "  \"phone\" : \"55565666\",\n" +
                "  \"courses\" : [ \"Java\", \"Go\" ]\n" +
                "}";


        //   (de-serialization)  convert Json object ---> POJO object
        ObjectMapper objectMapper = new ObjectMapper(); // import from com.fasterxml.jackson.databind.ObjectMapper;

        POJO_Student student = objectMapper.readValue(jsonData, POJO_Student.class);
        System.out.println("Name: " + student.getName());
        System.out.println("Location: " + student.getLocation());
        System.out.println("Phone: " + student.getPhone());
        System.out.println("Course1: " + student.getCourses()[0]);
        System.out.println("Course2: " + student.getCourses()[1]);


    }


}
