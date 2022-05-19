package sauceDemo.testCases.loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import sauceDemo.pages.InventoryPage;
import sauceDemo.pages.LoginPage;

import java.time.Duration;

public class LoginValidUser {

    ChromeDriver driver;
    LoginPage loginpage;

    @BeforeClass
    public void build() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void loginValidUserTest() {
        // Assert that no error messages are visible before login attempt
        // Log in and assert that driver lands on inventory page
        this.loginpage = LoginPage.visitPage(driver);
        Assert.assertTrue(loginpage.getErrorMessage().isEmpty());
        loginpage.login(loginpage.getStandardUsername(),loginpage.getValidPassword());
        Assert.assertEquals(driver.getCurrentUrl(), InventoryPage.url);
    }
}
