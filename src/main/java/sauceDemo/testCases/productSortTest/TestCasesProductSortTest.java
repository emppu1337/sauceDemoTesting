package sauceDemo.testCases.productSortTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sauceDemo.pages.InventoryPage;
import sauceDemo.pages.LoginPage;

import java.util.Collections;
import java.util.List;

public class TestCasesProductSortTest {

    InventoryPage inventoryPage;
    LoginPage loginPage;
    List<String> productNames;

    private void openInventoryPageAsUser(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
    }

    public void assertSortingAToZ(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortNameAToZ();
        this.productNames = inventoryPage.productNames();

        String previous = "";
        for (final String current : productNames) {
            Assert.assertTrue(previous.compareTo(current) < 0);
            previous = current;
        }
    }

    public void assertSortingZToA(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortNameZToA();
        this.productNames = inventoryPage.productNames();
        Collections.reverse(productNames);

        String previous = "";
        for (final String current : productNames) {
            Assert.assertTrue(previous.compareTo(current) < 0);
            previous = current;
        }
    }
}
