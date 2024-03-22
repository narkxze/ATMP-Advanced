package reportportal.hooks;

import reportportal.drivers.BrowserFactory;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}
