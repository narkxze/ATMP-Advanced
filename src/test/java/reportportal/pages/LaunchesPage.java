package reportportal.pages;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.openqa.selenium.By;
import reportportal.containers.NavigationContainer;

import java.util.List;
import java.util.Map;

import static reportportal.utils.NavigationPredicates.FILTERS_SELECTOR;
import static reportportal.utils.NavigationPredicates.performNavValidations;

public class LaunchesPage extends BasePage {
    private final static By launchesToolBar = By.xpath("//div[contains(@class,'launchFiltersToolbar__launch-filters')]");
    private final By moreDropdown = By.cssSelector("div[class*='entitiesSelector__toggler']");
    private final By attributeKeyInput = By.cssSelector("input[placeholder='Key']");
    private final By attributeKeyAutoComplete = By.xpath("//span[contains(@class,'autocompleteOption__label')]");
    private final By attributeValAutoComplete = By.xpath("(//span[contains(@class,'autocompleteOption__label')])[2]");
    private final By attributeValueInput = By.cssSelector("input[placeholder='Value']");
    private final By attributeCheckIcon = By.xpath("//div[contains(@class,'check-icon')]");
    private final By saveFilterCriteria = By.cssSelector("button[title='Save']");
    private final By updateFilterCriteria = By.xpath("//button[text()='Update']");
    private final By modalWindow = By.xpath("//div[contains(@class,'modal-window')]");
    private final By filterNameInput = By.cssSelector("input[placeholder='Enter filter name']");
    private final By filterAddBtn = By.xpath("//div[contains(@class,'modal-window')]//button[contains(text(),'Add')]");
    private final By successToast = By.xpath("//div[contains(@class,' notificationItem__success')]");
    private final By attributeConditionalsDropdown = By.xpath("//div[contains(@class,'inputConditionalAttributes__conditions-selector')]");
    private final By filterConditionalDropdown = By.xpath("(//div[contains(@class,'inputConditional__conditions-selector')])[2]");
    private final By quantityInput = By.cssSelector("input[placeholder='Enter quantity']");
    private final By editBtn = By.xpath("//button//span[text()='Edit']");
    private final By filterDescriptionInput = By.xpath("(//div[contains(@class,'markdownEditor')]//textarea)[2]");

    @Override
    public boolean verify() {
        return waitUntilVisible(launchesToolBar);
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
        click(moreDropdown);
        click(getLocatorForEntity(entity));
    }

    public void selectAttributeCondition(String attributeCondition) {
        click(attributeConditionalsDropdown);
        click(getLocatorForAttributeCondition(attributeCondition));
    }

    public void enterKeyAndValueForAttribute(String key, String value) {
        enterValue(attributeKeyInput, key);
        waitUntilVisible(attributeKeyAutoComplete);
        click(attributeKeyAutoComplete);
        enterValue(attributeValueInput, value);
        waitUntilVisible(attributeValAutoComplete);
        click(attributeValAutoComplete);
        click(attributeCheckIcon);

    }

    public void saveFilterCriteria(String filterName, NavigationContainer navigationContainer) {
        click(saveFilterCriteria);
        waitUntilVisible(modalWindow);
        enterValue(filterNameInput, filterName);
        click(filterAddBtn);
        waitUntilInvisible(successToast);
        performNavValidations(FILTERS_SELECTOR, navigationContainer);
    }

    public void fillFilterParameters(String condition, String quantity) {
        click(filterConditionalDropdown);
        click(getLocatorForFilterCondition(condition));
        enterValue(quantityInput, quantity);
    }

    public boolean bulkCreateAndValidateFilters(List<Map<String, String>> filterParametersList, FiltersPage filtersPage, NavigationContainer navigationContainer) {
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
            saveFilterCriteria(filter.get("FilterName"), navigationContainer);
            isFilterCreated = filtersPage.checkPresenceOfFilter(filter.get("FilterName"));
            filtersPage.clickAddFilterButton();
            return isFilterCreated;
        });

    }

    public boolean editFilterName(String newFilterName, FiltersPage filtersPage, NavigationContainer navigationContainer) {
        click(editBtn);
        waitUntilVisible(modalWindow);
        enterValue(filterNameInput, newFilterName);
        click(updateFilterCriteria);
        waitUntilInvisible(successToast);
        performNavValidations(FILTERS_SELECTOR, navigationContainer);
        return filtersPage.checkPresenceOfFilter(newFilterName);
    }

    public boolean editFilterDescription(String filterName, String newFilterDescription, FiltersPage filtersPage, NavigationContainer navigationContainer) {
        click(editBtn);
        waitUntilVisible(modalWindow);
        enterValue(filterDescriptionInput, newFilterDescription);
        click(updateFilterCriteria);
        waitUntilInvisible(successToast);
        performNavValidations(FILTERS_SELECTOR, navigationContainer);
        return filtersPage.validatePresenceOfDescription(filterName, newFilterDescription);
    }

    public boolean editFiltersInLaunchesPage(List<Map<String, String>> filters, FiltersPage filtersPage, NavigationContainer navigationContainer) {
        return filters.stream().allMatch(filter -> {
            filtersPage.openFilterAndNavigateToLaunches(filter.get("FilterName"));
            if (!filter.get("UpdatedFilterName").isBlank()) {
                return editFilterName(filter.get("UpdatedFilterName"), filtersPage, navigationContainer);
            } else if (!filter.get("UpdatedDescription").isBlank()) {
                return editFilterDescription(filter.get("FilterName"), filter.get("UpdatedDescription"), filtersPage, navigationContainer);
            } else {
                throw new NotImplementedException("Filter Field Not Implemented Yet for Editing");
            }
        });
    }
}
