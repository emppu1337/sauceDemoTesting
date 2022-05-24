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

    public void mustSortByPriceHiLo(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortPriceHiLo();
    }

    public void mustSortByPriceLoHi(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortPriceLoHi();
    }

    public void mustSortByNameAtoZ(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortNameAToZ();
    }

    public void mustSortByNameZtoA(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortNameZToA();
    }
    public void assertSortingAToZ(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortNameAToZ();
        this.productNames = inventoryPage.productNames();

        String previous = "";
        for (final String current : productNames) {
            Assert.assertTrue(previous.compareTo(current) < 0);
            System.out.println(current);
            previous = current;
        }
    }

    public void assertSortingZToA(WebDriver driver) {
        openInventoryPageAsUser(driver);
        inventoryPage.sortNameZToA();
        this.productNames = inventoryPage.productNames();
        System.out.println(productNames);
        Collections.reverse(productNames);
        System.out.println(productNames);

        String previous = "";
        for (final String current : productNames) {
            Assert.assertTrue(previous.compareTo(current) < 0);
            System.out.println(current);
            previous = current;

        }
    }
}
