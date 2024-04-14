package pog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;

import java.time.Duration;

public class UserLogout {

    protected final WebDriver driver;

    public UserLogout(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameBox = By.xpath("//label[text()=\"Имя\"]/..//input");
    private final By emailBox = By.xpath("//label[text()=\"Email\"]/..//input");
    private final By passwordBox = By.xpath("//*[text()=\"Пароль\"]/..//input");
    private final By signUp = By.xpath("//button[text()=\"Зарегистрироваться\"]");
    private final By wrongPasswordMessage = By.xpath("//p[text()=\"Некорректный пароль\"]");
    private final By signIn = By.xpath("//a[text()=\"Войти\"]");
    private final By signUpForm = By.xpath("//main//h2[text()=\"Регистрация\"]");

    public void insertUserLogOutData(User user) {
        insertName(user.getName());
        insertEmail(user.getEmail());
        insertPassword(user.getPassword());
    }

    public void insertName(String name) {
        driver.findElement(nameBox).sendKeys(name);
    }

    public void insertEmail(String email) {
        driver.findElement(emailBox).sendKeys(email);
    }

    public void insertPassword(String password) {
        driver.findElement(passwordBox).sendKeys(password);
    }

    public void clickSignUpButton() {
        driver.findElement(signUp).click();
    }

    public void clickSignInLink() {
        driver.findElement(signIn).click();
    }

    public boolean checkSignUpWrongPasswordError() {
        return driver.findElement(wrongPasswordMessage).isDisplayed();
    }

    public void waitingForSignUpPageLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(signUpForm));
    }
}
