package reportportal.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class BrowserFactory {
    private static final Supplier<WebDriver> CHROME = () -> {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    };
    private static final Supplier<WebDriver> FIREFOX = () -> {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    };

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    private static Map<String, Supplier<WebDriver>> BROWSERS;

    static {
        BROWSERS = Map.of("CHROME", CHROME, "FIREFOX", FIREFOX);
    }


    public static WebDriver getDriverInstance() {
        String browser = System.getenv("BROWSER");
        driver.set(BROWSERS.get(browser).get());
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
        wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(60)));
    }


    public static void quitDriver() {
        getActiveDriver().quit();
        wait.remove();
        driver.remove();
    }

    public static void launchUrl(String url) {
        getDriverInstance().get(url);
    }

}
