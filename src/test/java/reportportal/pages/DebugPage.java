package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reportportal.annotations.PageName;

@PageName("Debug")
public class DebugPage extends BasePage {
    private final static By refinePanel = By.xpath("//div[contains(@class,'refineFiltersPanel__refine-filters')]");

    @Override
    public boolean verify() {
        return waitUntilVisible(refinePanel);
    }
}
