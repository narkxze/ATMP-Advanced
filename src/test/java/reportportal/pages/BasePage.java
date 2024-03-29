package reportportal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.drivers.BrowserFactory;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BasePage {

    public static final Logger logger = LogManager.getLogger();

    WebDriver driver;
    WebDriverWait wait;

    public WebDriverWait getExplicitWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        }
        return wait;
    }

    public WebDriver getDriver() {
        driver = BrowserFactory.getDriverInstance();
        return driver;
    }

    public abstract boolean verify();


}
