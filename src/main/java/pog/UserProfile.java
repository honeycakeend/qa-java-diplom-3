package pog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfile{

    protected final WebDriver driver;

    public UserProfile(WebDriver driver) {
        this.driver = driver;
    }

    private final By constructorInHeader = By.xpath("//p[contains(@class, AppHeader_header) and text()=\"Конструктор\"]");
    private final By logoInHeader = By.xpath("//div[contains(@class, \"AppHeader_header__logo\")]");
    private final By profileInHeader = By.xpath("//a[contains(@class, \"Account_link\") and text()=\"Профиль\"]");
    private final By exitProfile = By.xpath("//button[contains(@class, \"Account_button\") and text()=\"Выход\"]");

    public void clickLogo() {
        driver.findElement(logoInHeader).click();
    }

    public void clickExitProfileButton() {
        driver.findElement(exitProfile).click();
    }

    public void clickConstructorInHeader() {
        driver.findElement(constructorInHeader).click();
    }

    public void waitProfilePageLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(profileInHeader));
    }
}
