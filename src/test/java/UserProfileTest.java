import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import pageobject.Main;
import pageobject.UserLogin;
import pageobject.UserProfile;
import user.User;
import user.UserGenerate;
import user.UserSteps;
import utils.Rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserProfileTest extends Rest{
    @Rule
    public TestUtils testUtils = new TestUtils();
    public Main main;
    public static String accessToken, refreshToken;
    public static User user;
    public WebDriver webDriver;
    public UserProfile userProfile;
    public UserLogin userLogin;

    @Before
    public void setUp() {
        webDriver = testUtils.getDriver();
        main = new Main(webDriver);
        userLogin = new UserLogin(webDriver);
        userProfile = new UserProfile(webDriver);
        webDriver.get(BASE_URL);
        LocalStorage localStorage = ((WebStorage) webDriver).getLocalStorage();
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);
    }

    @BeforeClass
    public static void beforeClass() {
        restBaseUri();
        user = UserGenerate.createRandom();
        Response response = UserSteps.create(user);
        accessToken = response.path("accessToken");
        refreshToken = response.path("refreshToken");
    }

    @AfterClass
    public static void afterClass() {
        UserSteps.delete(accessToken);
    }

    @Test
    @DisplayName("Personal cabinet. Logout")
    public void checkProfileExitButton() {
        webDriver.get(BASE_URL + "/account");
        userProfile.waitProfilePageLoading();
        userProfile.clickExitProfileButton();
        userLogin.waitingForLoginFormLoading();
        LocalStorage localStorage = ((WebStorage) webDriver).getLocalStorage();
        assertNull("Токен пользователя пустой", localStorage.getItem("accessToken"));
        assertEquals("Зашли на страницу логина после выхода", Rest.BASE_URL + Rest.LOGIN, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Checking the transition to the main page after user authorization")
    public void checkTransitionToMainPageAfterUserLoggedIn() {
        webDriver.get(BASE_URL + "/account");
        userProfile.waitProfilePageLoading();
        userProfile.clickLogo();
        main.waitLoadMain();
        assertEquals(BASE_URL + "/", webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Open main page from personal cabinet by clicking the button with the logo in the header")
    public void checkTransitionFromProfileByClickOnLogoButton() {
        webDriver.get(BASE_URL + "/account");
        userProfile.waitProfilePageLoading();
        userProfile.clickConstructorInHeader();
        main.waitLoadMain();
        assertEquals(BASE_URL + "/", webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Access to the user cabinet from the home page")
    public void checkFromMainToPersonalPassage() {
        main.waitLoadMain();
        main.clickOnProfileEnterButton();
        userProfile.waitProfilePageLoading();
        assertEquals(BASE_URL + PROFILE, webDriver.getCurrentUrl());
    }
}
