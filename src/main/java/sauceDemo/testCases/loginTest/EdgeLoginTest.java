package sauceDemo.testCases.loginTest;

import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sauceDemo.pages.LoginPage;
import sauceDemo.utilities.TearDown;
import sauceDemo.utilities.WebDriverSetup;

public class EdgeLoginTest {

    EdgeDriver driver;
    TestCasesLoginTest testExecution;
    LoginPage loginPage;
    WebDriverSetup setup;

    @BeforeClass
    public void setup() {
        this.setup = new WebDriverSetup();
        this.driver = setup.edgeSetup();
        this.testExecution = new TestCasesLoginTest();
        this.loginPage = new LoginPage(driver);
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
    public void noAccessToInventoryWithoutLogin() {
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
}