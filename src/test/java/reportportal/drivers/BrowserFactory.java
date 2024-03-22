package reportportal.drivers;

import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.function.Supplier;

public class BrowserFactory {
    private static final Supplier<Browser> CHROME = Chrome::new;
    private static final Supplier<Browser> FIREFOX = Firefox::new;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final Map<String, Supplier<Browser>> BROWSERS = Map.of("CHROME", CHROME, "FIREFOX", FIREFOX);

    public static WebDriver getDriverInstance(String browser) {
        driver.set(BROWSERS.get(browser).get().getDriver());
        return driver.get();
    }

    public static WebDriver getDriverInstance() {
        return driver.get();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }


}
