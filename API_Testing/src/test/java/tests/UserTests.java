package tests;

import com.github.javafaker.Faker;
import endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import payload_POJO.User;

public class UserTests {
    Faker faker;
    User userPayload;

    public Logger logger;


    @BeforeClass
    public void setupDate() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Logs
        logger = LogManager.getLogger(this.getClass());
    }


    @Test(priority = 1)
    public void testPOSTuser() {
        logger.info("*** Creating user ***");
        Response response = UserEndPoints.createUSer(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*** User is created ***");

    }

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("*** Reading user information ***");

        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("*** User info is displayed ***");
    }

    @Test(priority = 3)
    public void updateUserByName() {
        logger.info("*** Updating user information **");

        // update data using payload
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());


        Response response = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        // for checking how updating was passed
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().body();
        logger.info("*** User is updated **");
    }

    @Test (priority = 4)
    public void testDeleteUserByName() {
        logger.info("*** Deleting user _ ***");

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("*** User deleted ***");

    }


}
