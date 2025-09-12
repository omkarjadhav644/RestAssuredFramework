package com.EndPoints;

public class Routes {

    public static String base_url= "https://petstore.swagger.io/v2";

    //User module API endpoints

    public static String user_post_url=base_url+"/user";
    public static String user_get_url=base_url+"/user/{username}";
    public static String user_update_url=base_url+"/user/{username}";
    public static String user_delete_url=base_url+"/user/{username}";
}
