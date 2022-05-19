package sauceDemo.utilities;

import org.openqa.selenium.WebDriver;

public class TearDown {

    public static void tearDown (WebDriver driver) {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
