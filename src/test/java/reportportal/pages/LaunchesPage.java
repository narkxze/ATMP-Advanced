package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LaunchesPage extends BasePage {

    private static ThreadLocal<LaunchesPage> INSTANCE = null;

    private final static By launchesToolBar = By.xpath("//div[contains(@class,'launchFiltersToolbar__launch-filters')]");

    public static LaunchesPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(launchesToolBar)).isDisplayed();
    }
}
