package reportportal.pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reportportal.annotations.PageName;

@PageName("Settings")
public class SettingsPage extends BasePage {
    private final static By settingsLayout = By.xpath("//div[contains(@class,'settingsLayout__section')]");

    @Override
    public boolean verify() {
        return isDisplayed(settingsLayout);
    }
}
