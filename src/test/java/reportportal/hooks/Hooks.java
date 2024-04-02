package reportportal.hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;

import java.io.File;
import java.io.IOException;

import static reportportal.drivers.BrowserFactory.*;

public class Hooks {

    @After
    public void tearDown() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) getActiveDriver();
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        Allure.addAttachment("Test Evidence", FileUtils.openInputStream(screenshot));
        quitDriver();
    }
}
