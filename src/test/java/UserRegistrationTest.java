import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageobject.UserLogin;
import pageobject.UserLogout;
import user.User;
import user.UserGenerate;
import user.UserSteps;
import utils.Rest;

public class UserRegistrationTest extends Rest {
    @Rule
    public TestUtils testUtils = new TestUtils();
    public WebDriver webDriver;
    public UserLogout userLogout;
    public UserLogin userLogin;
    public static String accessToken;

    @Before
    public void setUp() {
        restBaseUri();
        webDriver = testUtils.getDriver();
        userLogout = new UserLogout(webDriver);
        webDriver.get(BASE_URL + REGISTER);
        userLogout.waitingForSignUpPageLoading();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @AfterClass
    public static void afterClass() {
        UserSteps.delete(accessToken);
    }


    @Test
    @DisplayName("Registration with valid data")
    public void signUpTest() {
        User user = UserGenerate.createRandom();
        userLogin = new UserLogin(webDriver);
        userLogout.insertUserLogOutData(user);
        userLogout.clickSignUpButton();
        userLogin.waitingForLoginFormLoading();
        Assert.assertEquals(BASE_URL + LOGIN, webDriver.getCurrentUrl());
        Response response = UserSteps.login(user);
        Assert.assertEquals(200, response.statusCode());
        accessToken = response.path("accessToken");
    }

    @Test
    @DisplayName("Registration with invalid password")
    public void signUpWithShortPasswordTest() {
        User user = UserGenerate.createRandom();
        user.setPassword("1111");
        userLogout.insertUserLogOutData(user);
        userLogout.clickSignUpButton();
        Assert.assertTrue(userLogout.checkSignUpWrongPasswordError());
        Assert.assertEquals(BASE_URL + REGISTER, webDriver.getCurrentUrl());
        Response response = UserSteps.login(user);
        Assert.assertFalse(response.path("success"));
    }
}
