package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


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
    private final By forgotPass = By.xpath("//a[contains(@class,'forgot-pass')]");


    public static LoginPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        logger.info("Current Opened Page Title:"+getDriver().getTitle());
        logger.info("Current Opened Page URL: "+getDriver().getCurrentUrl());
        return getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).isDisplayed();
    }

    public void enterUsername(String username) {
        getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(usernameTextBox)).sendKeys(username);
    }

    public void enterPassword(String password) {
        getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(passwordTextBox)).sendKeys(password);
    }

    public void clickLogin() {
        getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(loginBtn)).click();
    }

    public void clickLoginWithEPAM() {
        getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(loginWithEPAM)).click();
        getDriver().findElement(loginWithEPAM).click();
    }

    public void enterEPAMUsername(String username) {
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(usernameEPAMTextBox)).sendKeys(username);
    }

    public void enterEPAMPassword(String password) {
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(passwordEPAMTextBox)).sendKeys(password);
    }

    public void clickNext() {
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(nextBtn)).click();
    }

    public void usePinHyperLink() {
        getDriver().findElement(usePIN).click();
    }

    public Boolean getLoginErrorMessage(String errorMsg) {
        return getExplicitWait().until(ExpectedConditions.textToBePresentInElementLocated(loginErrLabel, errorMsg));
    }
}
