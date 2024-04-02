package reportportal.pages;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reportportal.containers.NavigationContainer;

import java.util.List;
import java.util.Map;

public class LaunchesPage extends BasePage {
    private static ThreadLocal<LaunchesPage> INSTANCE = null;
    private final static By launchesToolBar = By.xpath("//div[contains(@class,'launchFiltersToolbar__launch-filters')]");
    private NavigationContainer navigationContainer = (NavigationContainer) PageFactory.getPageInstance("NAVIGATION");
    private FiltersPage filtersPage = (FiltersPage) PageFactory.getPageInstance("FILTERS");
    private final By moreDropdown = By.cssSelector("div[class*='entitiesSelector__toggler']");
    private final  By attributeKeyInput = By.cssSelector("input[placeholder='Key']");
    private final  By attributeKeyAutoComplete = By.xpath("//span[contains(@class,'autocompleteOption__label')]");
    private final  By attributeValAutoComplete = By.xpath("(//span[contains(@class,'autocompleteOption__label')])[2]");
    private final  By attributeValueInput = By.cssSelector("input[placeholder='Value']");
    private final  By attributeCheckIcon = By.xpath("//div[contains(@class,'check-icon')]");
    private final  By saveFilterCriteria = By.cssSelector("button[title='Save']");
    private final  By modalWindow = By.xpath("//div[contains(@class,'modal-window')]");
    private final  By filterNameInput = By.cssSelector("input[placeholder='Enter filter name']");
    private final  By filterAddBtn = By.xpath("//div[contains(@class,'modal-window')]//button[contains(text(),'Add')]");
    private final  By successToast = By.xpath("//div[contains(@class,' notificationItem__success')]");
    private final  By attributeConditionalsDropdown = By.xpath("//div[contains(@class,'inputConditionalAttributes__conditions-selector')]");
    private final  By filterConditionalDropdown = By.xpath("(//div[contains(@class,'inputConditional__conditions-selector')])[2]");
    private final  By quantityInput = By.cssSelector("input[placeholder='Enter quantity']");
    private final  By editBtn = By.xpath("//button//span[text()='Edit']");
    private final  By filterDescriptionInput = By.xpath("//pre[text()='Enter filter description']");

    public static LaunchesPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
            INSTANCE.set(new LaunchesPage());
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
        return isDisplayed(launchesToolBar);
    }
    public By getLocatorForEntity(String entity) {
        return By.xpath(String.format("//div[contains(@class,'entity-item')]//span[contains(text(),'%s')]", entity));
    }

    public By getLocatorForAttributeCondition(String attributeCondition) {
        return By.xpath(String.format("//div[contains(@class,'inputConditionalAttributes__condition')]/div[text()='%s']", attributeCondition));
    }

    public By getLocatorForFilterCondition(String condition) {
        return By.xpath(String.format("//div[contains(@class,'inputConditional__condition')]/div[text()='%s']", condition));
    }

    public void selectEntity(String entity) {
        getDriver().findElement(moreDropdown).click();
        getDriver().findElement(getLocatorForEntity(entity)).click();
    }

    public void selectAttributeCondition(String attributeCondition) {
        getDriver().findElement(attributeConditionalsDropdown).click();
        getDriver().findElement(getLocatorForAttributeCondition(attributeCondition)).click();
    }

    public void enterKeyAndValueForAttribute(String key, String value) {
        getDriver().findElement(attributeKeyInput).sendKeys(key);
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(attributeKeyAutoComplete)).isDisplayed();
        getDriver().findElement(attributeKeyAutoComplete).click();
        getDriver().findElement(attributeValueInput).sendKeys(value);
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(attributeValAutoComplete)).isDisplayed();
        getDriver().findElement(attributeValAutoComplete).click();
        getDriver().findElement(attributeCheckIcon).click();
    }

    public void saveFilterCriteria(String filterName) {
        getDriver().findElement(saveFilterCriteria).click();
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(modalWindow)).isDisplayed();
        getDriver().findElement(filterNameInput).clear();
        getDriver().findElement(filterNameInput).sendKeys(filterName);
        getDriver().findElement(filterAddBtn).click();
        getExplicitWait().until(ExpectedConditions.invisibilityOfElementLocated(successToast));
        navigationContainer.validateFiltersSelector();
    }

    public void fillFilterParameters(String condition, String quantity) {
        getDriver().findElement(filterConditionalDropdown).click();
        getDriver().findElement(getLocatorForFilterCondition(condition)).click();
        getDriver().findElement(quantityInput).sendKeys(quantity);
    }


    public boolean bulkCreateAndValidateFilters(List<Map<String, String>> filterParametersList) {
        return filterParametersList.stream().allMatch(filter -> {
            boolean isFilterCreated;
            String entity = filter.get("Entity");
            selectEntity(entity);
            if (entity.equals("Attribute")) {
                selectAttributeCondition(filter.get("AttributeConditions"));
                enterKeyAndValueForAttribute(filter.get("AttrKey"), filter.get("AttrVal"));
            } else {
                fillFilterParameters(filter.get("Conditions"), filter.get("Quantity"));
            }
            saveFilterCriteria(filter.get("FilterName"));
            isFilterCreated = filtersPage.checkPresenceOfFilter(filter.get("FilterName"));
            filtersPage.clickAddFilterButton();
            return isFilterCreated;
        });

    }

    public boolean editFilterName(String newFilterName) {
        getDriver().findElement(editBtn).click();
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(modalWindow)).isDisplayed();
        getDriver().findElement(filterNameInput).clear();
        getDriver().findElement(filterNameInput).sendKeys(newFilterName);
        getDriver().findElement(saveFilterCriteria).click();
        getExplicitWait().until(ExpectedConditions.invisibilityOfElementLocated(successToast));
        navigationContainer.validateFiltersSelector();
        return filtersPage.checkPresenceOfFilter(newFilterName);
    }

    public boolean editFilterDescription(String filterName, String newFilterDescription) {
        getDriver().findElement(editBtn).click();
        getExplicitWait().until(ExpectedConditions.visibilityOfElementLocated(modalWindow)).isDisplayed();
        getDriver().findElement(filterDescriptionInput).sendKeys(newFilterDescription);
        getDriver().findElement(saveFilterCriteria).click();
        getExplicitWait().until(ExpectedConditions.invisibilityOfElementLocated(successToast));
        navigationContainer.validateFiltersSelector();
        return filtersPage.validatePresenceOfDescription(filterName, newFilterDescription);
    }

    public boolean editFiltersInLaunchesPage(Map<String, String> filter) {
        if (filter.containsKey("UpdatedFilterName")) {
            return editFilterName(filter.get("UpdatedFilterName"));
        } else if (filter.containsKey("UpdatedDescription")) {
            return editFilterDescription(filter.get("FilterName"), filter.get("UpdatedDescription"));
        } else {
            throw new NotImplementedException("Filter Field Not Implemented Yet for Editing Purposes");
        }
    }
}
