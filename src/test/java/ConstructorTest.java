import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pog.Main;
import utils.Rest;

import static org.junit.Assert.assertTrue;

public class ConstructorTest{

    @Rule
    public TestUtils testUtils = new TestUtils();
    public Main main;
    @Before
    public void setUp() {
        WebDriver webDriver = testUtils.getDriver();
        main = new Main(webDriver);
        webDriver.get(Rest.BASE_URL);
    }
    @Test
    public void checkSaucesPass() {
        main.clickOnSaucesMenu();
        assertTrue(main.saucesMenuIsSelected());
    }

    @Test
    public void checkFillingsPass() {
        main.clickOnFillingsMenu();
        assertTrue(main.fillingsMenuIsSelected());
    }

    @Test
    public void checkBunsPass() {
        main.clickOnFillingsMenu();
        main.clickOnBunsMenu();
        assertTrue(main.bunsMenuIsSelected());
    }
}
