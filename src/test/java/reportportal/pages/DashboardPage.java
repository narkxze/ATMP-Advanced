package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    private final By dashBoardTitle = By.xpath("//span[@title='All Dashboards']");


    @Override
    public boolean verify() {
        return waitUntilVisible(dashBoardTitle);
    }
}
