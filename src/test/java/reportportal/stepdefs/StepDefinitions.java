package reportportal.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.*;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;
import reportportal.containers.NavigationContainer;
import reportportal.drivers.BrowserFactory;
import reportportal.enums.LoginEnum;
import reportportal.enums.NavigationEnum;
import reportportal.pages.*;
import reportportal.steps.BaseSteps;
import reportportal.utils.EnvironmentUtils;
import reportportal.utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static reportportal.drivers.BrowserFactory.launchUrl;
import static reportportal.utils.EnvironmentUtils.getEnvironmentValue;
import static reportportal.utils.LoginConsumer.performLogin;
import static reportportal.utils.NavigationPredicates.performNavValidations;

@AllArgsConstructor
public class StepDefinitions {
    PageManager pageManager;
    BaseSteps baseSteps;

    @Given("I launch Report Portal")
    public void launchReportPortal() {
        BrowserFactory.launchUrl(System.getenv("LOCAL_HOST_URL"));
        //  launchUrl("http://localhost:8080/ui/#login");
    }

    @And("I enter {string} for login")
    public void iEnterCredentials(String expectedFlow) {
        Consumer<LoginPage> loginConsumer = LoginEnum.valueOf(expectedFlow.toUpperCase()).getLoginConsumer();
        performLogin(loginConsumer, pageManager.getLoginPage());
    }

    @But("I see the error {string}")
    public void theLoginAuthorizationErrorIsDisplayed(String errorMsg) {
        assertTrue(pageManager.getLoginPage().getLoginErrorMessage(errorMsg));
    }

    @And("I validate {string} elements from Navigation Bar")
    @And("I navigate to {string} page from the Navigation Bar")
    public void iValidateNavContainerElements(String pane) {
        Predicate<NavigationContainer> navPredicates = NavigationEnum.valueOf(pane.toUpperCase()).getNavPredicates();
        performNavValidations(navPredicates, pageManager.getNavigationContainer());
    }

    @When("I click Add Filters")
    public void clickAddFilters() {
        pageManager.getFiltersPage().clickAddFilterButton();
    }

    @Then("I create and validate the creation of filters named in {string} sheet of {string} file")
    public void bulkCreateAndValidate(String sheetName, String fileName) throws IOException {
        List<Map<String, String>> filtersParams = ExcelReader.readCells(sheetName, fileName);
        assertTrue("Filters are not created", pageManager.getLaunchesPage().bulkCreateAndValidateFilters(filtersParams, pageManager.getFiltersPage(), pageManager.getNavigationContainer()));
    }

    @Then("I delete and validate the absence of filters named in {string} sheet of {string} file")
    public void bulkDeleteAndValidate(String sheetName, String fileName) throws IOException {
        List<Map<String, String>> filtersParams = ExcelReader.readCells(sheetName, fileName);
        assertTrue("Filters are not deleted", pageManager.getFiltersPage().bulkDeleteAndValidateAbsence(filtersParams));
    }

    @Then("I edit and validate the change of filters named in {string} sheet of {string} file")
    public void bulkEditAndValidate(String sheetName, String fileName) throws IOException {
        List<Map<String, String>> filtersParams = ExcelReader.readCells(sheetName, fileName);
        System.out.println(filtersParams);
        assertTrue("Filters are not deleted", pageManager.getLaunchesPage().editFiltersInLaunchesPage(filtersParams, pageManager.getFiltersPage(), pageManager.getNavigationContainer()));
    }

    @When("^I enter '([^\"]*)' to the '([^\"]*)' field$")
    public void typeValueToField(String value, String field) {
        baseSteps.enterValue(getEnvironmentValue(value), field);
    }

    @When("I click on {string} button")
    public void clickButton(String element) {
        baseSteps.clickButton(element);
    }

    @Then("The element {string} contains text {string}")
    public void containsText(String element, String textValue) {
        assertTrue(baseSteps.validateText(element, textValue));
    }

    @Then("The element {string} does not contains text {string}")
    public void doesNotContainsText(String element, String textValue) {
        assertFalse(baseSteps.validateText(element, textValue));
    }

    @When("I fill the following fields:")
    public void enterCredentials(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps();
        for (Map<String, String> map : maps) {
            baseSteps.enterValue(getEnvironmentValue(map.get("value")), map.get("field"));
        }
    }

    @Then("I should be in {string} Page")
    public void validateCurrentPage(String pageName) {
        assertTrue(baseSteps.validatePage(pageName));
    }

    @When("I scroll into the view of {string} element")
    public void scrollToView(String element) {
        baseSteps.scrollToView(element);
    }

    @And("I toggle the display of {string} to {string}")
    public void toggleFilterDisplayOnLaunches(String filterName, String targetToggle) {
        pageManager.getFiltersPage().toggleFilterDisplay(filterName, targetToggle);
    }
}
