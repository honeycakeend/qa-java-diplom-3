package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main{

    protected final WebDriver driver;

    public Main(WebDriver driver) {
        this.driver = driver;
    }

    private final By createOrderButton = By.xpath("//button[text()=\"Оформить заказ\"]");
    private final By profileButton = By.xpath("//p[text()=\"Личный Кабинет\"]");
    private final By accountEnterButton = By.xpath("//button[text()=\"Войти в аккаунт\"]");
    private final By burgerTitle = By.xpath("//*[text()=\"Соберите бургер\"]");
    private final By bunsInMenu = By.xpath("//span[text()=\"Булки\"]/..");
    private final By saucesInMenu = By.xpath("//span[text()=\"Соусы\"]/..");
    private final By fillingInMenu = By.xpath("//span[text()=\"Начинки\"]/..");

    public boolean orderPlaceButtonIsDisplayed() {
        return driver.findElement(createOrderButton).isDisplayed();
    }

    public void clickOnProfileEnterButton() {
        driver.findElement(profileButton).click();
    }

    public void clickOnAccountEnterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(accountEnterButton).click();
    }

    public void clickOnBunsMenu() {
        driver.findElement(bunsInMenu).click();
    }

    public void clickOnSaucesMenu() {
        driver.findElement(saucesInMenu).click();
    }

    public void clickOnFillingsMenu() {
        driver.findElement(fillingInMenu).click();
    }

    public boolean bunsMenuIsSelected() {
        return driver.findElement(bunsInMenu).getAttribute("class").contains("current");
    }

    public boolean saucesMenuIsSelected() {
        return driver.findElement(saucesInMenu).getAttribute("class").contains("current");
    }

    public boolean fillingsMenuIsSelected() {
        return driver.findElement(fillingInMenu).getAttribute("class").contains("current");
    }

    public void waitLoadMain() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerTitle));
    }
}
