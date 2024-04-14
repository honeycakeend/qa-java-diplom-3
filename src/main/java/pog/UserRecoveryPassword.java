package pog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserRecoveryPassword{

    protected final WebDriver driver;

    public UserRecoveryPassword(WebDriver driver) {
        this.driver = driver;
    }

    private final By signIn = By.xpath("//a[text()=\"Войти\"]");

    public void clickSignIn() {
        driver.findElement(signIn).click();
    }
}
