package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.Rest.*;

public class UserSteps{

    @Step("Create user")
    public static Response create(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(API_REGISTER);
    }

    @Step("Login user")
    public static Response login(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(API_LOGIN);
    }

    @Step("Delete user")
    public static Response delete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(API_USER);
    }
}
