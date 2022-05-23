package sauceDemo.utilities;

import org.openqa.selenium.WebDriver;

public class ClearCookie {

    public static void removeLoginCookies(WebDriver driver, String cookieName) {
        try {
            driver.manage().deleteCookieNamed(cookieName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}