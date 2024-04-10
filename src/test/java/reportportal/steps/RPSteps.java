package reportportal.steps;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reportportal.containers.NavigationContainer;
import reportportal.enums.LoginEnum;
import reportportal.enums.NavigationEnum;
import reportportal.pages.*;
import reportportal.utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.testng.AssertJUnit.assertTrue;
import static reportportal.drivers.BrowserFactory.launchUrl;
import static reportportal.utils.LoginConsumer.performLogin;
import static reportportal.utils.NavigationPredicates.performNavValidations;


public class RPSteps {
    PageManager pageManager = new PageManager();

    public static final Logger logger = LogManager.getLogger();


    @Given("I launch Report Portal")
    public void launchReportPortal() {
        launchUrl(System.getenv("LOCAL_HOST_URL"));
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

    @And("I should be in {string} Page")
    public void validatePage(String pageName) {
        switch (pageName) {
            case "Login":
                pageManager.getLoginPage().verify();
                break;
            case "Dashboard":
                pageManager.getDashboardPage().verify();
                break;
            case "Filters":
                pageManager.getFiltersPage().verify();
            default:
                throw new IllegalArgumentException("Not landed on the correct page.");
        }
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

}
