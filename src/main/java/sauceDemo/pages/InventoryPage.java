package sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    public static final String url = "https://www.saucedemo.com/inventory.html";
    public WebDriver driver;

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

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public static InventoryPage visitPage(WebDriver driver) {
        InventoryPage page = new InventoryPage(driver);
        page.getPage();
        return page;
    }

    // Method to get to this page
    public void getPage() {
        this.driver.get(url);
    }

    // Method to press a button
    public void pressButton(WebElement button) {
        button.click();
    }
}
