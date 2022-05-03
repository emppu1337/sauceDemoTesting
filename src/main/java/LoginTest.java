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

    // TODO: 4/29/2022 Add more better assertions
    // TODO: 4/29/2022 Current JS script that forces to wait for page to load can't really handle exceptions, do something or make better
    // TODO: 4/29/2022 Learn how to run test on Edge, Firefox and Chrome parallel
    // TODO: 4/29/2022 Learn how to implement parameters with testng.xml

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

    @Test(priority = 1)
    public void standardUser() {
        String userName = "standard_user";
        String password = "secret_sauce";
        System.out.println(driver.manage().getCookies());

        login(userName, password);
        Assert.assertEquals("https://www.saucedemo.com/inventory.html"
                ,driver.getCurrentUrl()
                ,"login must work with valid credentials");
        System.out.println(driver.manage().getCookies());
    }

    @Test(priority = 3)
    public void problemUser() {
        String userName = "problem_user";
        String password = "secret_sauce";
        login(userName, password);
        Assert.assertEquals("https://www.saucedemo.com/inventory.html"
                ,driver.getCurrentUrl()
                ,"login must work with valid credentials");
    }

    @Test(priority = 3)
    public void performanceGlitchUser() {
        String userName = "performance_glitch_user";
        String password = "secret_sauce";
        login(userName, password);
        Assert.assertEquals("https://www.saucedemo.com/inventory.html"
                ,driver.getCurrentUrl()
                ,"login must work with valid credentials");
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

/*Comparing Strings With Constant Values

When comparing a String to a constant value, you can put the constant value on the left side of equals to ensure that you won’t get a NullPointerException if the other String is null.
For example:

"baz".equals(foo)
While foo.equals("baz") will throw a NullPointerException if foo is null, "baz".equals(foo) will evaluate to false.

A more readable alternative is to use Objects.equals(), which does a null check on both parameters:
e.g. Objects.equals(foo, "baz").
 */