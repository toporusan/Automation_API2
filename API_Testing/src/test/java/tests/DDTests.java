package tests;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload_POJO.User;
import utilities.DataProviders;

public class DDTests {


    // dataProviderClass = DataProviders.class если провайдер и тесты находятся в разных классах/папках

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPOSTUser(String userId, String userName, String firstName, String lastName, String email, String password, String ph) {

        User userPayload = new User();
        userPayload.setId(Integer.parseInt((String) userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(ph);


        Response response = UserEndPoints.createUSer(userPayload);
        // response.then().log().body();

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("---------------testPOSTUser-------------");

    }

    @Test(priority = 2, dataProvider = "UsersName", dataProviderClass = DataProviders.class) // dataProviderClass = DataProviders.class если провайдер и тесты находятся в разных классах/папках
    public void deleteUserByName(String userName) {

       Response response = UserEndPoints.deleteUser(userName);

        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("------------deleteUserByName------->>-----");

    }

}
