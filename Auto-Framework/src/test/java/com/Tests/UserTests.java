package com.Tests;
import com.EndPoints.UserEndpoints;
import com.Payloads.User;
import com.Utilities.UserAssertions;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class UserTests {
    Faker faker;
    User userPayload;

    @BeforeClass()
    public void setData(){
        faker = new Faker();
        userPayload= new User();
        System.out.println(Arrays.toString(User.class.getMethods()));

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 0,description = "Create new user with POST request")
    public void testUserCreation() {

        Response response= UserEndpoints.createUser(userPayload);
        response.then().log().all();

        UserAssertions.assertUserCreatedWithRetry(userPayload.getUsername(), 8, 1000);

    }

    @Test(priority = 1,description = "Get user information with username with GET request")
    public void testGetUserByName() {
        System.out.println("testGetUserByName-->"+userPayload.getUsername());
        UserAssertions.assertUserCreatedWithRetry(userPayload.getUsername(), 8, 1000);
    }


    @Test(priority = 2,description = "Update the details of user using PUT request")
    public void testUpdateUser() {

        String lastNameUpdate =faker.name().lastName();
        this.userPayload.setLastName(lastNameUpdate);
        Response response= UserEndpoints.updateUser(this.userPayload, this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        UserAssertions.assertUserUpdatedWithRetry(userPayload.getUsername(), lastNameUpdate,8, 1000);

    }

    @Test(priority = 3,description = "Delete the user using DELETE request")
    public void testDeleteUser() {

        Response response= UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        Response response_readUser = UserEndpoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(response_readUser.getStatusCode(),404);

    }

}
