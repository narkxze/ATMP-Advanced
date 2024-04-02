package reportportal.pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SettingsPage extends BasePage {
    private static ThreadLocal<SettingsPage> INSTANCE = null;
    private final static By settingsLayout = By.xpath("//div[contains(@class,'settingsLayout__section')]");

    public static SettingsPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
            INSTANCE.set(new SettingsPage());
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return isDisplayed(settingsLayout);
    }
}
