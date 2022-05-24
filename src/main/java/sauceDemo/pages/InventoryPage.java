package sauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InventoryPage {

    public static final String url = "https://www.saucedemo.com/inventory.html";
    public WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    Select select;
    List<WebElement> inventoryItems;

    @FindBy (id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy (id = "react-burger-cross-btn")
    WebElement closeMenuButton;

    @FindBy (id = "inventory_sidebar_link")
    WebElement allItemsItem;

    @FindBy (id = "about_sidebar_link")
    WebElement aboutItem;

    @FindBy (id = "logout_sidebar_link")
    WebElement logoutItem;

    @FindBy (id = "reset_sidebar_link")
    WebElement resetAppStateItem;

    @FindBy (xpath = "/html/body/div/div/div/div[1]/div[2]/div[2]/span/select")
    WebElement productSortContainer;

    @FindBy (id = "inventory_container")
    WebElement inventoryContainer;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // Method to press a button
    public void pressButton(WebElement button) {
        button.click();
    }
    public void pressMenuButton() {
        pressButton(menuButton);
    }
    public void pressCloseMenuButton() {
        pressButton(closeMenuButton);
    }
    public void pressAllItemsItem() {
        pressButton(allItemsItem);
    }
    public void pressAboutItem() {
        pressButton(aboutItem);
    }
    public void pressLogoutItem() {
        pressButton(logoutItem);
    }
    public void pressResetAppStateItem() {
        pressButton(resetAppStateItem);
    }

    public void logout() {
        pressButton(menuButton);
        pressButton(logoutItem);
    }

    public void sortPriceHiLo() {
        this.select = new Select(productSortContainer);
        select.selectByVisibleText("Price (low to high)");
    }
    public void sortPriceLoHi() {
        this.select = new Select(productSortContainer);
        select.selectByVisibleText("Price (high to low)");
    }
    public void sortNameAToZ() {
        this.select = new Select(productSortContainer);
        select.selectByVisibleText("Name (A to Z)");
    }
    public void sortNameZToA() {
        this.select = new Select(productSortContainer);
        select.selectByVisibleText("Name (Z to A)");
    }

    public List<String> productNames() {
        List<String> names = new LinkedList<>();
        List<WebElement> products = inventoryContainer.findElements(By.className("inventory_item"));
        for (WebElement product : products) {
            names.add(product.findElement(By.className("inventory_item_name")).getText());
        }
        return names;
    }
}
