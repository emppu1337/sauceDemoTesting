import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotAccessibleWithLockedAccount {

    String BASE_URL = "https://www.saucedemo.com/";
    ChromeDriver driver;

    @BeforeMethod
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterMethod
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void errorMessageNotVisibleBeforeLoginAttempt() {
        // Open the main page of test object to assert error message is not visible before login attempt
        driver.get("https://www.saucedemo.com");
        Assert.assertEquals(driver.getTitle().toLowerCase(), "swag labs", "could not get www.saucedemo.com");

        // Error message container itself is always displayed, but attribute "class" changes from "error-message-container" to "error-message-container error" when error message is shown
        // Get error message container and assert
        WebElement errorMessageContainer = driver.findElement(By.cssSelector("#login_button_container > div > form > div:nth-child(3)"));
        Assert.assertFalse(errorMessageContainer.getAttribute("class").contains(" error"));
    }

    @Test
    public void lockedUser() {
        String userName = "locked_out_user";
        String password = "secret_sauce";

        login(userName, password);
        Assert.assertNotEquals("https://www.saucedemo.com/inventory.html"
                , driver.getCurrentUrl()
                , "login must not work with locked user");
        WebElement errorMessageContainer = driver.findElement(By.cssSelector("#login_button_container > div > form > div:nth-child(3)"));
        Assert.assertTrue(errorMessageContainer.getAttribute("class").contains(" error"));
        Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMessageContainer.getText(), "something wrong with error message");
    }

    public void login(String username, String password) {
        try {
            driver.get(BASE_URL);
            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
            while (!("complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState")))) {
                driver.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}