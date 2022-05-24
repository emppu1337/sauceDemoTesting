package sauceDemo.testCases.productSortTest;

import groovy.util.logging.Log;
import org.openqa.selenium.WebDriver;
import sauceDemo.pages.InventoryPage;
import sauceDemo.pages.LoginPage;

public class TestCasesProductSortTest {

    InventoryPage inventoryPage;
    LoginPage loginPage;

    public void mustSortByPriceHiLo(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
        inventoryPage.sortPriceHiLo();
    }
    public void mustSortByPriceLoHi(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
        inventoryPage.sortPriceLoHi();
    }
    public void mustSortByNameAtoZ(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
        inventoryPage.sortNameAToZ();
    }
    public void mustSortByNameZtoA(WebDriver driver) {
        this.loginPage = LoginPage.visitPage(driver);
        this.inventoryPage = loginPage.getInventoryPage(loginPage.getStandardUsername(), loginPage.getValidPassword());
        inventoryPage.sortNameZToA();
    }

}
