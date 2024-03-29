package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FiltersPage extends BasePage {
    private static ThreadLocal<FiltersPage> INSTANCE = null;

    private final static By filterToolBar = By.xpath("//div[contains(@class,'filterPageToolbar__filter-search')]");

    public static FiltersPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(filterToolBar)).isDisplayed();
    }
}
