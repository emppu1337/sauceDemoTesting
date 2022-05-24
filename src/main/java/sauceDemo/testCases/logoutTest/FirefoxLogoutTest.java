package sauceDemo.testCases.logoutTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sauceDemo.utilities.TearDown;
import sauceDemo.utilities.WebDriverSetup;

public class FirefoxLogoutTest {
    WebDriver driver;
    TestCasesLogoutTest testExecution;
    WebDriverSetup setup;

    @BeforeClass
    public void setup() {
        this.setup = new WebDriverSetup();
        this.driver = setup.firefoxSetup();
        this.testExecution = new TestCasesLogoutTest();
    }
    @AfterClass
    public void tearDown() {
        TearDown.tearDown(driver);
    }

    @Test
    public void logoutMustDeleteLoginCookie() {
        testExecution.logoutMustRemoveLoginCookies(driver);
    }
    @Test
    public void logoutMustLandOnLoginPage() {
        testExecution.mustReturnToLoginPageAfterLogout(driver);
    }
    @Test
    public void mustHaveNoAccessToInventoryAfterLogout() {
        testExecution.noAccessToInventoryAfterLogout(driver);
    }

}
