package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reportportal.annotations.Element;
import reportportal.annotations.PageName;

import static reportportal.pages.LoginPage.Controls.*;

@PageName("Login")
public class LoginPage extends BasePage {
    @Element(USERNAME)
    private String usernameBox = "//input[@name='login']";

    @Element(PASSWORD)
    private String passwordBpx = "//input[@name='password']";

    @Element(LOGIN)
    private String login = "//button[@type='submit']";

    private final By usernameTextBox = By.cssSelector("input[name='login']");
    private final By passwordTextBox = By.cssSelector("input[name='password']");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By loginErrLabel = By.xpath("//div[contains(@class,'notification')]/p");

    @Override
    public boolean verify() {
        return isDisplayed(loginBtn);
    }

    public void enterUsername(String username) {
        enterValue(usernameTextBox, username);
    }

    public void enterPassword(String password) {
        enterValue(passwordTextBox, password);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public Boolean getLoginErrorMessage(String errorMsg) {
        return getExplicitWait().until(ExpectedConditions.textToBePresentInElementLocated(loginErrLabel, errorMsg));
    }

    public static class Controls {
        public final static String USERNAME = "Username";
        public final static String PASSWORD = "Password";
        public final static String LOGIN = "Login";
    }

}
