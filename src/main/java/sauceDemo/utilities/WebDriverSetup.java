package sauceDemo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverSetup {

    public static ChromeDriver chromeDriverSetup(int maxTimeOut) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTimeOut));
        return driver;
    }
    public static EdgeDriver edgeSetup(int maxTimeOut) {
        WebDriverManager.edgedriver().setup();
        EdgeDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTimeOut));
        return driver;
    }
    public static FirefoxDriver firefoxSetup(int maxTimeOut) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTimeOut));
        return driver;
    }
}
