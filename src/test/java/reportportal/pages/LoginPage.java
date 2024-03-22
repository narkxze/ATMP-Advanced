package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.KeyEvent;


public class LoginPage extends BasePage {
    private static ThreadLocal<LoginPage> INSTANCE = null;
    private final By usernameTextBox = By.cssSelector("input[name='login']");
    private final By passwordTextBox = By.cssSelector("input[name='password']");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By loginWithEPAM = By.cssSelector("button[type='button']");
    private final By loginErrLabel = By.xpath("//div[contains(@class,'notification')]/p");
    private final By usernameEPAMTextBox = By.cssSelector("input[type='email']");
    private final By nextBtn = By.cssSelector("input[type='submit']");
    private final By passwordEPAMTextBox = By.cssSelector("input[type='password']");
    private final By usePIN = By.xpath("//a[contains(@id,'PWD_Switch')]");

    public static LoginPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).isDisplayed();
    }

    public void enterUsername(String username) {
        getDriver().findElement(usernameTextBox).sendKeys(username);
    }

    public void enterPassword(String password) {
        getDriver().findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLogin() {
        getDriver().findElement(loginBtn).click();
    }

    public void clickLoginWithEPAM() {
        getDriver().findElement(loginWithEPAM).click();
    }

    public void enterEPAMUsername(String username) {
       getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(usernameEPAMTextBox)).sendKeys(username);
    }

    public void enterEPAMPassword(String password) {
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(passwordEPAMTextBox)).sendKeys(password);
    }

    public void clickNext() {
        getDriver().findElement(nextBtn).click();
    }

    public void usePinHyperLink() {
        getDriver().findElement(usePIN).click();
    }
    public Boolean getLoginErrorMessage(String errorMsg) {
        return getExplicitWait().until(ExpectedConditions.textToBePresentInElementLocated(loginErrLabel, errorMsg));
    }
}
