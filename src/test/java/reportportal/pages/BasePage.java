package reportportal.pages;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.annotations.Element;
import reportportal.drivers.BrowserFactory;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
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

    public void enterValue(WebElement element, String value) {
        getExplicitWait().until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
    }

    @SneakyThrows
    public WebElement getElement(String fieldName) {
        Class<? extends BasePage> clazz = this.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Element.class)) {
                Element annotation = field.getAnnotation(Element.class);
                if (annotation.value().equals(fieldName)) {
                    field.setAccessible(true);
                    String xpath = (String) field.get(this);
                    return getDriver().findElement(By.xpath(xpath));
                }
            }
        }
        return null;
    }

    public void click(WebElement ele) {
        getExplicitWait().until(ExpectedConditions.elementToBeClickable(ele)).click();
    }
}
