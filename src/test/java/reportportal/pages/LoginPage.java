package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class LoginPage extends BasePage {
    private final By usernameTextBox = By.cssSelector("input[name='login']");
    private final By passwordTextBox = By.cssSelector("input[name='password']");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By loginErrLabel = By.xpath("//div[contains(@class,'notification')]/p");

    @Override
    public boolean verify() {
        logger.info("Current Opened Page Title:" + getDriver().getTitle());
        logger.info("Current Opened Page URL: " + getDriver().getCurrentUrl());
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

}
