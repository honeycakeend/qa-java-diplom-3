import lombok.Getter;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import utils.Driver;

import java.time.Duration;

public class TestUtils extends ExternalResource {
    @Getter
    private WebDriver driver;

    String property = System.getProperty("browser", "chrome");

    @Override
    protected void before() {
        driver = Driver.getDriver(property);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
