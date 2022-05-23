package sauceDemo.testCases.errorMessageTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sauceDemo.utilities.TearDown;
import sauceDemo.utilities.WebDriverSetup;

public class EdgeErrorMessageTest {
    WebDriver driver;
    TestCasesErrorMessageTest testExecution;
    WebDriverSetup setup;

    @BeforeClass
    public void setup() {
        this.setup = new WebDriverSetup();
        this.driver= setup.edgeSetup();
        this.testExecution = new TestCasesErrorMessageTest();
    }
    @AfterClass
    public void tearDown() {
        TearDown.tearDown(driver);
    }
    @Test
    public void noErrorMessageWithoutError() {
        testExecution.noErrorMessageWithoutError(driver);
    }
    @Test
    public void missingCredentials() {
        testExecution.missingCredentials(driver);
    }
    @Test
    public void lockedOutUser() {
        testExecution.lockedUser(driver);
    }
    @Test
    public void invalidUsername() {
        testExecution.invalidUsername(driver);
    }
    @Test
    public void invalidPassword() {
        testExecution.invalidPassword(driver);
    }
    @Test
    public void missingUsername() {
        testExecution.missingUsername(driver);
    }
    @Test
    public void missingPassword() {
        testExecution.missingPassword(driver);
    }
}
