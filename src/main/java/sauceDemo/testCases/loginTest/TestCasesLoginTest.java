package sauceDemo.testCases.loginTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sauceDemo.pages.InventoryPage;
import sauceDemo.pages.LoginPage;

public class TestCasesLoginTest {

    LoginPage loginpage;
    String[] cookiesToBeDeletedAfterMethod = new String[]{"session-username"};

    public void noAccessToInventoryWithoutLogin(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        driver.get(InventoryPage.url);
        Assert.assertNotEquals(driver.getCurrentUrl(), InventoryPage.url);
    }
    public void loginMustLandOnInventoryPage(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login("standard_user","secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), InventoryPage.url);
    }
    public void validLoginMustGiveAccessToInventory(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login("standard_user","secret_sauce");
        driver.get(InventoryPage.url);
        Assert.assertEquals(driver.getCurrentUrl(), InventoryPage.url);
    }
    public void lockedOutUserMustHaveNoAccess(WebDriver driver) {
        this.loginpage = LoginPage.visitPage(driver);
        loginpage.login(loginpage.getLockedUsername(),loginpage.getValidPassword());
        driver.get(InventoryPage.url);
        Assert.assertNotEquals(driver.getCurrentUrl(), InventoryPage.url);
    }
    public void deleteLoginCookies(WebDriver driver) {
        for (String cookieName : cookiesToBeDeletedAfterMethod) {
            try {
                driver.manage().deleteCookieNamed(cookieName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
