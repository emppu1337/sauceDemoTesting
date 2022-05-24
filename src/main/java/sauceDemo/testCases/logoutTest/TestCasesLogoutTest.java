package sauceDemo.testCases.logoutTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sauceDemo.pages.InventoryPage;
import sauceDemo.pages.LoginPage;

public class TestCasesLogoutTest {

    InventoryPage inventoryPage;
    LoginPage loginPage;

    public void logoutMustRemoveLoginCookies(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
        assertLoginSuccessful(driver);
        inventoryPage.logout();
        Assert.assertNull(driver.manage().getCookieNamed(LoginPage.loginCookieName));
    }
    public void noAccessToInventoryAfterLogout(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
        assertLoginSuccessful(driver);
        inventoryPage.logout();
        driver.get(InventoryPage.url);
        Assert.assertNotEquals(driver.getCurrentUrl(), InventoryPage.url);

    }
    public void mustReturnToLoginPageAfterLogout(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
        assertLoginSuccessful(driver);
        inventoryPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.url);
    }
    private void assertLoginSuccessful(WebDriver driver) {
        Assert.assertEquals(driver.getCurrentUrl(), InventoryPage.url);
    }
}
