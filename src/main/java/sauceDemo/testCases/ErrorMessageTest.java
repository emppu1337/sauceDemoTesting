package sauceDemo.testCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sauceDemo.pages.LoginPage;
import sauceDemo.utilities.WebDriverSetups;

public class ErrorMessageTest {

    /*  Class asserts that
        - error message is not visible when error has not occurred
        - correct error message is shown according to error in login attempt
        - error messages can be successfully closed with error message button
    */

    ChromeDriver driver;
    LoginPage loginpage;
    private String expectedErrorMessage;

    @BeforeClass
    public WebDriverSetups driver() {
        this.driver = (ChromeDriver) WebDriverSetups.chromeDriverSetup(driver);
        return null;
    }

    @AfterClass
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void noErrorMessageWithoutError() { // Assert that no error message is visible before login attempt
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
    }

    @Test
    public void lockedUser() { // Assert that correct error message is visible after login attempt
        this.expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login(loginpage.getLockedUsername(), loginpage.getValidPassword());
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    @Test
    public void missingUsername() { // Assert that correct error message is visible after login attempt
        this.expectedErrorMessage = "Epic sadface: Username is required";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login("", loginpage.getValidPassword());
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    @Test
    public void missingPassword() { // Assert that correct error message is visible after login attempt
        this.expectedErrorMessage = "Epic sadface: Password is required";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login(loginpage.getStandardUsername(), "");
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    @Test
    public void missingCredentials() { // Assert that correct error message is visible after login attempt
        this.expectedErrorMessage = "Epic sadface: Username is required";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login("", "");
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    @Test
    public void invalidUsername() { // Assert that correct error message is visible after login attempt
        this.expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login(loginpage.getInvalidUsername(), loginpage.getValidPassword());
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    @Test
    public void invalidPassword() { // Assert that correct error message is visible after login attempt
        this.expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login(loginpage.getStandardUsername(), loginpage.getInvalidPassword());
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    public void assertErrorMessageNotVisible() {
        Assert.assertFalse(loginpage.errorMessageContainerVisibility());
    }

    public void assertErrorMessageVisible() {
        Assert.assertTrue(loginpage.errorMessageContainerVisibility());
    }

    public void assertErrorMessage() {
        Assert.assertTrue(loginpage.getErrorMessage().equalsIgnoreCase(expectedErrorMessage));
    }
}