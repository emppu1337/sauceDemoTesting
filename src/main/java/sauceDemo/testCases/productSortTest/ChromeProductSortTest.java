package sauceDemo.testCases.productSortTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sauceDemo.utilities.TearDown;
import sauceDemo.utilities.WebDriverSetup;

public class ChromeProductSortTest {
    WebDriver driver;
    TestCasesProductSortTest testExecution;
    WebDriverSetup setup;

    @BeforeClass
    public void setup() {
        this.setup = new WebDriverSetup();
        this.driver = setup.chromeSetup();
        this.testExecution = new TestCasesProductSortTest();
    }
    @AfterClass
    public void tearDown() {
        TearDown.tearDown(driver);
    }

    @Test
    public void mustSortByPriceHiLo() {
        testExecution.mustSortByPriceHiLo(driver);
    }
    @Test
    public void mustSortByPriceLoHi() {
        testExecution.mustSortByPriceLoHi(driver);
    }
    @Test
    public void mustSortByNameZtoA() {
        testExecution.mustSortByNameAtoZ(driver);
    }
    @Test
    public void mustSortByNameAtoZ() {
        testExecution.mustSortByNameZtoA(driver);
    }
}
