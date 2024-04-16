package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;

import java.time.Duration;

public class UserLogin{

    protected final WebDriver driver;

    public UserLogin(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailInput = By.xpath("//label[text()=\"Email\"]/..//input");
    private final By passwordInput = By.xpath("//label[text()=\"Пароль\"]/..//input");
    private final By loginButton = By.xpath("//button[text()=\"Войти\"]");
    private final By loginFormEnter = By.xpath("//*[text() = \"Вход\"]");

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void enterUserDataAndClickLogin(User user) {
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        clickLoginButton();
    }

    public void waitingForLoginFormLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginFormEnter));
    }
}
