package reportportal.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class BrowserFactory {
    private static final Supplier<Browser> CHROME = Chrome::new;
    private static final Supplier<Browser> FIREFOX = Firefox::new;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private static final Map<String, Supplier<Browser>> BROWSERS = Map.of("CHROME", CHROME, "FIREFOX", FIREFOX);

    public static WebDriver getDriverInstance() {
        String browser = System.getenv("BROWSER");
        driver.set(BROWSERS.get(browser).get().getDriver());
        setupExplicitWait();
        return driver.get();
    }

    public static WebDriver getActiveDriver() {
        return driver.get();
    }

    public static WebDriverWait getActiveWait() {
        return wait.get();
    }

    public static void setupExplicitWait() {
        wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(20)));
    }


    public static void quitDriver() {
        getActiveDriver().quit();
        wait.remove();
        driver.remove();
    }

}
