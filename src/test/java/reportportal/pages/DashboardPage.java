package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    private static ThreadLocal<DashboardPage> INSTANCE = null;
    private final By dashBoardTitle = By.xpath("//span[@title='All Dashboards']");


    public static DashboardPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(dashBoardTitle)).isDisplayed();
    }
}
