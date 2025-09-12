package com.Utilities;

import com.EndPoints.UserEndpoints;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

public class UserAssertions {

    public static void assertUserCreatedWithRetry(String username, int maxRetries, long waitMillis) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                Response response = UserEndpoints.readUser(username);
                if (response.getStatusCode() == 200) {
                    System.out.println("User found With name : "+ username);
                    return;
                }
                Thread.sleep(waitMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted during wait", e);
            }
            attempt++;
        }
        throw new AssertionError("User not found after retries: " + username);
    }


    public static void assertUserUpdatedWithRetry(String username, String lastNameUpdate,int maxRetries, long waitMillis) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                Response response = UserEndpoints.readUser(username);
                JSONObject jo=  new JSONObject(response.asString());
                if (lastNameUpdate.equals(jo.get("lastName"))) {
                    System.out.println("User Last name is updated as "+ lastNameUpdate);
                    return;
                }
                Thread.sleep(waitMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted during wait", e);
            }
            attempt++;
        }
        throw new AssertionError("User not found after retries: " + username);
    }
}
