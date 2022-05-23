package sauceDemo.testCases.errorMessageTest;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import sauceDemo.pages.LoginPage;

public class TestCasesErrorMessageTest {

    LoginPage loginpage;
    private String expectedErrorMessage;

    @Test
    @Parameters ({
            "standardUser",
            "lockedOutUser",
            "validPassword",
            "invalidUser",
            "invalidPassword"
    })

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