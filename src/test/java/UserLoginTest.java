import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageobject.Main;
import pageobject.UserLogin;
import pageobject.UserLogout;
import pageobject.UserRecoveryPassword;
import user.User;
import user.UserGenerate;
import user.UserSteps;
import utils.Rest;

import static org.junit.Assert.assertTrue;

public class UserLoginTest extends Rest{

    @Rule
    public TestUtils testUtils = new TestUtils();
    public static WebDriver webDriver;
    public Main main;
    public static String accessToken;
    public static User user;
    public static UserLogin userLogin;

    @Before
    public void setUp() {
        webDriver = testUtils.getDriver();
        main = new Main(webDriver);
        userLogin = new UserLogin(webDriver);
        webDriver.get(BASE_URL);
    }

    @BeforeClass
    public static void beforeClass() {
        restBaseUri();
        user = UserGenerate.createRandom();
        accessToken = UserSteps.create(user).path("accessToken");
    }

    @AfterClass
    public static void afterClass() {
        UserSteps.delete(accessToken);
    }

    @Test
    @DisplayName("Authorization from the login page")
    public void checkLoginFromLoginPage() {
        webDriver.get(BASE_URL + LOGIN);
        assertions();
    }

    @Test
    @DisplayName("Personal Cabinet. Authorization by login and password")
    public void checkLoginFromMainPageProfileButton() {
        webDriver.get(BASE_URL);
        main.waitLoadMain();
        main.clickOnProfileEnterButton();
        assertions();
    }

    @Test
    @DisplayName("Button 'Sign  in to your account'")
    public void checkLoginFromMainPageByEmailAndPassword() {
        webDriver.get(BASE_URL);
        main.waitLoadMain();
        main.clickOnAccountEnterButton();
        assertions();
    }

    @Test
    @DisplayName("Registration page. Button to enter")
    public void checkLoginFromSignUpPage() {
        webDriver.get(BASE_URL + REGISTER);
        UserLogout logOut = new UserLogout(webDriver);
        logOut.clickSignInLink();
        assertions();
    }

    @Test
    @DisplayName("Password recovery page. Button authorization")
    public void checkLoginFromRecoveryPage() {
        webDriver.get(BASE_URL + FORGOT_PASS);
        UserRecoveryPassword passwordRecoveryPage = new UserRecoveryPassword(webDriver);
        passwordRecoveryPage.clickSignIn();
        assertions();
    }

    @Step("Address after authorization")
    public static boolean loginPageCorrectUrl() {
        UserLogin loginPage = new UserLogin(webDriver);
        loginPage.waitingForLoginFormLoading();
        return webDriver.getCurrentUrl().equals(BASE_URL + LOGIN);
    }

    @Step("Home page. Button 'Place an order")
    public static boolean mainPageIsLoadedAfterSuccessfulLogin() {
        Main mainPage = new Main(webDriver);
        mainPage.waitLoadMain();
        return mainPage.orderPlaceButtonIsDisplayed();
    }

    public static void assertions() {
        assertTrue(loginPageCorrectUrl());
        userLogin.enterUserDataAndClickLogin(user);
        assertTrue(mainPageIsLoadedAfterSuccessfulLogin());
    }
}
