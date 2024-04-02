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

//    @AfterStep(order = 0)
//    public void takeAndEmbedScreenshot(Scenario scenario) {
////        if (scenario.isFailed()) {
//        TakesScreenshot ts = (TakesScreenshot) getDriverInstance();
//        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
//        scenario.attach(screenshot, "image/png", "Scenario Attached");
////        }
//    }

//    @AfterStep(order = 0)
//    public void slowDownExecutions() throws InterruptedException {
//        Thread.sleep(500);
//    }


    @After
    public void tearDown() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) getActiveDriver();
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
//        scenario.attach(screenshot, "image/png", scenario.getName());
        Allure.addAttachment("Test Evidence", FileUtils.openInputStream(screenshot));
        
        quitDriver();
    }
}
