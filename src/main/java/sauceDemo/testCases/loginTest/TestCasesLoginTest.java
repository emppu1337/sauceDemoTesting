package sauceDemo.testCases.loginTest;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sauceDemo.pages.InventoryPage;
import sauceDemo.pages.LoginPage;

import java.util.Set;

public class TestCasesLoginTest {

    /*
    Test login-function and assert that successful login allows access to inventory and login lands on inventory page.
    Assert that inventory cannot be accessed without valid login.
    Login is saved as a cookie named "session-username". This cookie is deleted after every method to assert there
    are no multiple active logins.
    As per now, loginMustLandOnInventoryPage() and validLoginMustGiveAccessToInventory() could be tested in one method
    and thus there is redundancy. However, if webmaster was to change the landing page to, for example, a profile page
    or similar, the redundancy would be neglected.
    */

    LoginPage loginpage;
    String[] loginCookieNames = new String[]{"session-username"};

    public void noAccessToInventoryWithoutLogin(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        driver.get(InventoryPage.url);
        Assert.assertNotEquals(driver.getCurrentUrl(), InventoryPage.url);
    }

    public void validLoginMustGiveAccessToInventory(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login(loginpage.getStandardUsername(), loginpage.getValidPassword());
        driver.get(InventoryPage.url);
        Assert.assertEquals(driver.getCurrentUrl(), InventoryPage.url);
    }

    public void invalidPasswordMustNotGiveAccessToInventory(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login(loginpage.getStandardUsername(), loginpage.getInvalidPassword());
        driver.get(InventoryPage.url);
        Assert.assertNotEquals(driver.getCurrentUrl(), InventoryPage.url);
    }

    public void invalidUsernameMustNotGiveAccessToInventory(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login(loginpage.getInvalidUsername(), loginpage.getValidPassword());
        driver.get(InventoryPage.url);
        Assert.assertNotEquals(driver.getCurrentUrl(), InventoryPage.url);
    }

    public void lockedOutUserMustHaveNoAccess(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login(loginpage.getLockedUsername(),loginpage.getValidPassword());
        driver.get(InventoryPage.url);
        Assert.assertNotEquals(driver.getCurrentUrl(), InventoryPage.url);
    }

    public void loginMustLandOnInventoryPage(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login(loginpage.getStandardUsername(), loginpage.getValidPassword());
        Assert.assertEquals(driver.getCurrentUrl(), InventoryPage.url);
    }

    public void onlyOneLoginAllowedAtOnce(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login(loginpage.getStandardUsername(), loginpage.getValidPassword());
        // Save current login cookie as loginCookie
        Cookie loginCookie = driver.manage().getCookieNamed(loginCookieNames[0]);
        // Get visit login page for a second login
        this.loginpage = LoginPage.visitPage(driver);
        // Login using different credentials
        loginpage.login(loginpage.getProblemUsername(), loginpage.getValidPassword());
        // Get current cookies as a set called currentCookies
        Set<Cookie> currentCookies = driver.manage().getCookies();
        // Assert that currentCookies does not contain login cookie from the first login (loginCookie)
        Assert.assertFalse(currentCookies.contains(loginCookie));
    }

    public void deleteLoginCookies(WebDriver driver) {
        for (String cookieName : loginCookieNames) {
            try {
                driver.manage().deleteCookieNamed(cookieName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
