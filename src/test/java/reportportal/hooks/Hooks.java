package reportportal.hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;

import static reportportal.drivers.BrowserFactory.getDriverInstance;
import static reportportal.drivers.BrowserFactory.quitDriver;

public class Hooks {

    @AfterStep(order = 0)
    public void takeAndEmbedScreenshot(Scenario scenario) {
//        if (scenario.isFailed()) {
        TakesScreenshot ts = (TakesScreenshot) getDriverInstance();
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Scenario Attached");
//        }
    }

    @After(order = 1)
    public void tearDown() {
        quitDriver();
    }
}
