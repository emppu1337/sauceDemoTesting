package sauceDemo.testCases.errorMessageTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sauceDemo.pages.LoginPage;

public class TestCasesErrorMessageTest {

    /*
    Error message container on the login page is always visible (as an element).
    If content is shown value in attribute "class" for the error-message-container ends with " error".
    Test uses this attribute to assert that valid error message is shown in different scenarios of failed
    login attempt or failed access attempt.
    First it is asserted that error message is not visible before any of these attempts.
    Content of error-message-container is compared to expected error message and container is closed, after which
    it is asserted that error message is no visible.
    */

    // TODO: 30/05/2022 read expected error messages from a JSON or move them to Loginpage class and access them with getters

    LoginPage loginpage;
    private String expectedErrorMessage;

    //  Assert that no error message is visible before login attempt
    public void noErrorMessageWithoutError(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
    }

    // Assert that correct error message is visible after failed login attempt
    public void lockedUser(WebDriver driver) {
        this.expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login(loginpage.getLockedUsername(), loginpage.getValidPassword());
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    // Assert that correct error message is visible after failed login attempt
    public void missingUsername(WebDriver driver) {
        this.expectedErrorMessage = "Epic sadface: Username is required";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login("", loginpage.getValidPassword());
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    // Assert that correct error message is visible after failed login attempt
    public void missingPassword(WebDriver driver) {
        this.expectedErrorMessage = "Epic sadface: Password is required";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login(loginpage.getStandardUsername(), "");
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    // Assert that correct error message is visible after failed login attempt
    public void missingCredentials(WebDriver driver) {
        this.expectedErrorMessage = "Epic sadface: Username is required";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login("", "");
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    // Assert that correct error message is visible after failed login attempt
    public void invalidUsername(WebDriver driver) {
        this.expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        this.loginpage = LoginPage.visitPage(driver);
        assertErrorMessageNotVisible();
        loginpage.login(loginpage.getInvalidUsername(), loginpage.getValidPassword());
        assertErrorMessageVisible();
        assertErrorMessage();
        loginpage.clickErrorButton();
        assertErrorMessageNotVisible();
    }

    // Assert that correct error message is visible after failed login attempt
    public void invalidPassword(WebDriver driver) {
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