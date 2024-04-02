package reportportal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.drivers.BrowserFactory;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static reportportal.drivers.BrowserFactory.getActiveWait;

public abstract class BasePage {

    public static final Logger logger = LogManager.getLogger();
    private static String DRIVER_SESSION = null;

    WebDriver driver;

    WebDriverWait wait;

    public WebDriverWait getExplicitWait() {
        wait = getActiveWait();
        return wait;
    }

    public WebDriver getDriver() {
        driver = BrowserFactory.getActiveDriver();
        return driver;
    }

    public abstract boolean verify();

    public void click(By locator) {
        getExplicitWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public boolean isDisplayed(By locator) {
        return getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public WebElement waitUntilClickable(By locator) {
        return getExplicitWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilInvisible(By locator) {
        getExplicitWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitUntilVisible(By locator) {
        return getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public void enterValue(By locator, String value) {
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value);
    }
}
