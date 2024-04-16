package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {

    public static WebDriver getDriver(String browser){
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                WebDriver driver = new ChromeDriver(options);
                return driver;
            case "firefox":
                FirefoxOptions optionsF = new FirefoxOptions();
                return new FirefoxDriver(optionsF);
            default:
                throw new IllegalArgumentException("Browsers other than Chrome and FireFox are not supported yet");
        }
    }
}
