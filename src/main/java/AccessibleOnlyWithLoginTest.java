import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccessibleOnlyWithLoginTest {

    ChromeDriver driver;

    @BeforeMethod
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Test object saves login as a cookie with expiration time
        // Therefore, we can clear all active logins by deleting cookies
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void quitDriver() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void noAccessWithoutLogin() {

        // Open the main page of test object to assert error message is not visible before unauthorized access attempt
        driver.get("https://www.saucedemo.com");
        Assert.assertEquals(driver.getTitle().toLowerCase(), "swag labs", "could not get www.saucedemo.com");

        // Error message container itself is always displayed, but attribute "class" changes from "error-message-container" to "error-message-container error" when error message is shown
        // Get error message container and assert
        WebElement errorMessageContainer = driver.findElement(By.cssSelector("#login_button_container > div > form > div:nth-child(3)"));
        Assert.assertFalse(errorMessageContainer.getAttribute("class").contains(" error"));
        System.out.println(errorMessageContainer.getAttribute("class"));

        // Try to access the inventory without login
        driver.get("https://www.saucedemo.com/inventory.html");

        // Assert that user IS NOT directed to inventory-site
        Assert.assertNotEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl()
                ,"inventory must be accessible only to logged-in users");

        // Assert that error message is visible by checking that attribute "class" contains " error"
        errorMessageContainer = driver.findElement(By.cssSelector("#login_button_container > div > form > div:nth-child(3)"));
        Assert.assertTrue(errorMessageContainer.getAttribute("class").contains(" error"), "error message must be visible after unauthorized access attempt");
        System.out.println(errorMessageContainer.getAttribute("class"));

        // Assert that correct error message is shown
        Assert.assertEquals("Epic sadface: You can only access '/inventory.html' when you are logged in.", errorMessageContainer.getText(), "something wrong with error message");
    }
}