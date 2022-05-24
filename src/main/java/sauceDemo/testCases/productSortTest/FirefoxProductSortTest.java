package sauceDemo.testCases.productSortTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import sauceDemo.utilities.TearDown;
import sauceDemo.utilities.WebDriverSetup;

public class FirefoxProductSortTest {
    WebDriver driver;
    TestCasesProductSortTest testExecution;
    WebDriverSetup setup;

    @BeforeClass
    public void setup() {
        this.setup = new WebDriverSetup();
        this.driver = setup.firefoxSetup();
        this.testExecution = new TestCasesProductSortTest();
    }
    @AfterClass
    public void tearDown() {
        TearDown.tearDown(driver);
    }

}
