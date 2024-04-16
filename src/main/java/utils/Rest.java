package utils;

import io.restassured.RestAssured;

public class Rest {
    public final static String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public final static String REGISTER = "/register";
    public final static String LOGIN = "/login";
    public final static String FORGOT_PASS = "/forgot-password";
    public final static String PROFILE = "/account/profile";
    public final static String API_REGISTER = "/api/auth/register";
    public final static String API_LOGIN = "/api/auth/login";
    public final static String API_USER = "/api/auth/user";

    protected static String restBaseUri(){
        return RestAssured.baseURI = BASE_URL;
    }
}
