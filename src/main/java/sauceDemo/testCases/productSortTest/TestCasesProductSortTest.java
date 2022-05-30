package sauceDemo.testCases.productSortTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sauceDemo.pages.InventoryPage;
import sauceDemo.pages.LoginPage;

import java.util.Collections;
import java.util.List;

public class TestCasesProductSortTest {

    /*
    Test that sorting by alphabetical order works by sorting the products on inventory page and checking that they
    are in alphabetical or reverse alphabetical order (according to the sort)
    */

    InventoryPage inventoryPage;
    LoginPage loginPage;
    List<String> productNames;

    private void openInventoryPageAsUser(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
    }

    public void assertSortingAToZ(WebDriver driver) {
        openInventoryPageAsUser(driver);
        // Run A-to-Z sort of inventory items
        inventoryPage.sortNameAToZ();
        // Get the product names as a String array
        this.productNames = inventoryPage.productNames();
        // Use empty String to compare the first value and compare each String in the array to previous
        // Current becomes new previous after comparison
        String previous = "";
        for (final String current : productNames) {
            Assert.assertTrue(previous.compareTo(current) < 0);
            previous = current;
        }
    }

    public void assertSortingZToA(WebDriver driver) {
        // Does exactly the same as A-to-Z, but runs the sort Z-to-A and flips the array order around for comparison
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
