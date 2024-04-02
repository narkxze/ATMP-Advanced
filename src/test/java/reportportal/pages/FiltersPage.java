package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

public class FiltersPage extends BasePage {
    private static ThreadLocal<FiltersPage> INSTANCE = null;
    private final static By filterToolBar = By.xpath("//div[contains(@class,'filterPageToolbar__filter-search')]");
    private final LaunchesPage launchesPage = (LaunchesPage) PageFactory.getPageInstance("LAUNCHES");
    private final By addFiltersBtn = By.xpath("//button//span[text()='Add Filter']");
    private final By filterNames = By.xpath("//span[contains(@class,'filterName__bold')]");
    private final By modalWindow = By.xpath("//div[contains(@class,'modal-window')]");
    private final By deleteFilterBtn = By.xpath("//button[text()='Delete']");
    private final By deleteSuccessToast = By.xpath("//p[text()='Filter has been deleted!']");

    public static FiltersPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
            INSTANCE.set(new FiltersPage());
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return isDisplayed(filterToolBar);
    }
    public void clickAddFilterButton() {
        getDriver().findElement(addFiltersBtn).click();
    }

    public boolean checkPresenceOfFilter(String filterName) {
        return getDriver().findElements(filterNames).stream().anyMatch(filter -> filter.getText().contains(filterName));
    }

    public By getDeleteLocatorForFilter(String filterName) {
        return By.xpath(String.format("//span[contains(text(),'%s')]/ancestor::span/parent::div/following-sibling::div[contains(@class,'delete')]//div", filterName));
    }

    public void deleteNamedFilter(String filterName) {
        getDriver().findElement(getDeleteLocatorForFilter(filterName)).click();
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(modalWindow)).isDisplayed();
        getDriver().findElement(deleteFilterBtn).click();
        getExplicitWait().until(ExpectedConditions.invisibilityOfElementLocated(deleteSuccessToast));
    }

    public boolean validateAbsenceOfFilter(String filterName) {
        return getDriver().findElements(filterNames).stream().noneMatch(filter -> filter.getText().contains(filterName));
    }

    public boolean validatePresenceOfDescription(String filterName, String filterDesc) {
        return getDriver().findElement(getDescriptionLocatorForGivenFilterName(filterName, filterDesc)).isDisplayed();
    }

    public boolean bulkDeleteAndValidateAbsence(List<Map<String, String>> filters) {
        return filters.stream().allMatch(filter -> {
            String filterName = filter.get("FilterName");
            deleteNamedFilter(filterName);
            return validateAbsenceOfFilter(filterName);
        });
    }

    private By getLocatorForGivenFilterName(String filterName) {
        return By.xpath(String.format("//a[contains(@class,'filterName')]/span[contains(text(),'%s')]", filterName));
    }

    private By getDescriptionLocatorForGivenFilterName(String filterName, String filterDesc) {
        return By.xpath(String.format("//a[contains(@class,'filterName')]/span[contains(text(),'%s')]/ancestor::span//following::div//p[text()='Filter Description Updated']", filterName, filterDesc));
    }

    private void openFilterAndNavigateToLaunches(String targetFilter) {
        getDriver().findElement(getLocatorForGivenFilterName(targetFilter)).click();
        launchesPage.verify();
    }

    public boolean bulkEditFilters(List<Map<String, String>> filters) {
        return filters.stream().allMatch(filter -> {
            String targetFilter = filter.get("FilterName");
            openFilterAndNavigateToLaunches(targetFilter);
            return launchesPage.editFiltersInLaunchesPage(filter);
        });
    }
}
