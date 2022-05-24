package sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static final String url = "https://www.saucedemo.com/";
    public static final String loginCookieName = "session-username";

    public WebDriver driver;

    // Use @FindBy annotations to locate necessary elements on the page
    @FindBy (id = "user-name")
    WebElement inputUsername;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "#login_button_container > div > form > div:nth-child(3)")
    WebElement errorMessageContainer;

    @FindBy(css = ".error-button")
    WebElement errorButton;

    // Page needs a WebDriver to find elements -> constructor is needed
    // initElements initializes elements on page
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public static LoginPage visitPage(WebDriver driver) {
        LoginPage page = new LoginPage(driver);
        page.getPage();
        return page;
    }

    // Method to get to this page
    public void getPage() {
        this.driver.get(url);
    }

    // Method to perform login
    public void login(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        clickLoginButton();
    }

    public InventoryPage getInventoryPage(String username, String password) {
        login(username, password);
        return PageFactory.initElements(driver, InventoryPage.class);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickErrorButton() {
        errorButton.click();
    }

    public String getErrorMessage() {
        return errorMessageContainer.getText();
    }
    public boolean errorMessageContainerVisibility() {
        return errorMessageContainer.getAttribute("class").contains(" error");
    }

    public String getStandardUsername() {
        return "standard_user";
    }

    public String getLockedUsername() {
        return "locked_out_user";
    }

    public String getProblemUsername() {
        return "problem_user";
    }

    public String getPerformanceGlitchUsername() {
        return "performance_glitch_user";
    }

    public String getValidPassword() {
        return "secret_sauce";
    }

    public String getInvalidUsername() {
        return "invalid_username";
    }

    public String getInvalidPassword() {
        return "invalid_password";
    }
}
