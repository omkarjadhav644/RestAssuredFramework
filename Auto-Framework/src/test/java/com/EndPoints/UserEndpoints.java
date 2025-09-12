package com.EndPoints;

import com.Payloads.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndpoints {

    public static Response createUser(User payload){

        RequestSpecification rs = RestAssured.given();
        rs.accept(ContentType.JSON);
        rs.contentType(ContentType.JSON);
        rs.body(payload);

        Response response= rs.post(Routes.user_post_url);

        return response;
    }

    public static Response readUser(String username){

        RequestSpecification rs = RestAssured.given();
        rs.pathParam("username",username);

        Response response = rs.get(Routes.user_get_url);

        return response;
    }


    public static Response updateUser(User payload, String username){

        RequestSpecification rs = RestAssured.given();
        rs.accept(ContentType.JSON);
        rs.contentType(ContentType.JSON);
        rs.body(payload);
        rs.pathParam("username",username);



        Response response= rs.put(Routes.user_update_url);

        return response;
    }

    public static Response deleteUser(String username){

        RequestSpecification rs = RestAssured.given();
        rs.pathParam("username",username);

        Response response = rs.delete(Routes.user_delete_url);

        return response;
    }

}
