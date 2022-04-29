import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    // TODO: 4/29/2022 Learn how to run test on Edge, Firefox and Chrome parallel
    // TODO: 4/29/2022 Learn how to implement parameters with testng.xml

    String BASE_URL = "https://www.saucedemo.com/";
    WebDriver driver;

    @BeforeMethod
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterMethod
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void standardUser() {
        String userName = "standard_user";
        String password = "secret_sauce";
        login(userName, password);
        Assert.assertEquals(driver.getCurrentUrl()
                ,("https://www.saucedemo.com/inventory.html")
                ,"login must work with valid credentials");
    }

    @Test(priority = 2)
    public void lockedUser() {
        String userName = "locked_out_user";
        String password = "secret_sauce";
        login(userName, password);
        Assert.assertNotEquals(driver.getCurrentUrl()
                ,("https://www.saucedemo.com/inventory.html")
                ,"login must not work with locked user");
    }

    @Test(priority = 3)
    public void problemUser() {
        String userName = "problem_user";
        String password = "secret_sauce";
        login(userName, password);
        Assert.assertEquals(driver.getCurrentUrl()
                ,("https://www.saucedemo.com/inventory.html")
                ,"login must work with valid credentials");
    }

    @Test(priority = 4)
    public void performanceGlitchUser() {
        String userName = "performance_glitch_user";
        String password = "secret_sauce";
        login(userName, password);
        Assert.assertEquals(driver.getCurrentUrl()
                ,("https://www.saucedemo.com/inventory.html")
                ,"login must work with valid credentials");
    }

    public void login(String username, String password) {
        try {
            driver.get(BASE_URL);
            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
            while (!(((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"))) {
                driver.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}