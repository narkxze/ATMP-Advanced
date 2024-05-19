package reportportal.pages;

import org.openqa.selenium.By;
import reportportal.annotations.Element;
import reportportal.annotations.PageName;

@PageName("Dashboard")
public class DashboardPage extends BasePage {
    private final static String ADD_FILTERS = "Add Filters";

    @Element(ADD_FILTERS)
    private String addFiltersBtn = "//button//span[text()='Add Filter']";

    private final By dashBoardTitle = By.xpath("//span[@title='All Dashboards']");

    @Override
    public boolean verify() {
        return waitUntilVisible(dashBoardTitle);
    }
}
