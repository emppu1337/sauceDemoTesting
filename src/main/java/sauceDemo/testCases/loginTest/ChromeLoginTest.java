package sauceDemo.testCases.loginTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sauceDemo.utilities.TearDown;
import sauceDemo.utilities.WebDriverSetup;

public class ChromeLoginTest {

    WebDriver driver;
    TestCasesLoginTest testExecution;
    WebDriverSetup setup;

    @BeforeClass
    public void setup() {
        this.setup = new WebDriverSetup();
        this.driver = setup.chromeSetup();
        this.testExecution = new TestCasesLoginTest();
    }
    @AfterClass
    public void tearDown() {
        TearDown.tearDown(driver);
    }

    @AfterMethod
    public void removeLoginCookies() {
        testExecution.deleteLoginCookies(driver);
    }

    @Test
    public void noAccessWithoutLogin() {
        testExecution.noAccessToInventoryWithoutLogin(driver);
    }
    @Test
    public void validLoginMustLandOnInventoryPage() {
        testExecution.loginMustLandOnInventoryPage(driver);
    }
    @Test
    public void validLoginGivesAccessToInventory() {
        testExecution.validLoginMustGiveAccessToInventory(driver);
    }
    @Test
    public void lockedOutUserMustHaveNoAccess() {
        testExecution.lockedOutUserMustHaveNoAccess(driver);
    }
    @Test
    public void onlyOneLoginAllowedAtATime() {testExecution.onlyOneLoginAllowedAtOnce(driver);
    }
    @Test
    public void invalidUsernameMustNotHaveAccessToInventory() {testExecution.invalidUsernameMustNotGiveAccessToInventory(driver);}
    @Test
    public void invalidPasswordMustNotGiveAccessToInventory() {testExecution.invalidPasswordMustNotGiveAccessToInventory(driver);}
}
