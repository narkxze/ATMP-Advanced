package reportportal.drivers;

import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface Browser {

    public WebDriver getDriver();
}
