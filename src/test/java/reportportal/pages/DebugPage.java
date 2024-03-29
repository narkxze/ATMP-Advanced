package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DebugPage extends BasePage {
    private static ThreadLocal<DebugPage> INSTANCE = null;

    private final static By refinePanel = By.xpath("//div[contains(@class,'refineFiltersPanel__refine-filters')]");

    public static DebugPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(refinePanel)).isDisplayed();
    }
}
