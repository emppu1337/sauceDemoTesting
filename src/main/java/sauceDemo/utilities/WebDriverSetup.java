package sauceDemo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverSetup {

    private final int maxTimeOut = 10;

    public ChromeDriver chromeSetup() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTimeOut));
        return driver;
    }

    public EdgeDriver edgeSetup() {
        WebDriverManager.edgedriver().setup();
        EdgeDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTimeOut));
        return driver;
    }

    public FirefoxDriver firefoxSetup() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTimeOut));
        return driver;
    }
}
