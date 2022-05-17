package sauceDemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static final String url = "https://www.saucedemo.com";

    private final String standardUsername = "standard_user";
    private final String lockedUsername = "locked_out_user";
    private final String problemUsername = "problem_user";
    private final String performanceGlitchUsername = "performance_glitch_user";
    private final String validPassword = "secret_sauce";
    private final String invalidUsername = "invalid_username";
    private final String invalidPassword = "invalid_password";

    public ChromeDriver driver;

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
    public LoginPage(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public static LoginPage visitPage(ChromeDriver driver) {
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
    public void clickLoginButton() {
        loginButton.click();
    }
    public void clickErrorButton() {
        errorButton.click();
    }

    public String getErrorMessage() {
        String errorMessage = errorMessageContainer.getText();
        return errorMessage;
    }
    public boolean errorMessageContainerVisibility() {
        return errorMessageContainer.getAttribute("class").contains(" error");
    }

    public String getStandardUsername() {
        return standardUsername;
    }

    public String getLockedUsername() {
        return lockedUsername;
    }

    public String getProblemUsername() {
        return problemUsername;
    }

    public String getPerformanceGlitchUsername() {
        return performanceGlitchUsername;
    }

    public String getValidPassword() {
        return validPassword;
    }

    public String getInvalidUsername() {
        return invalidUsername;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }
}
