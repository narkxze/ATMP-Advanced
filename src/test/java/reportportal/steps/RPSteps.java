package reportportal.steps;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import reportportal.containers.NavigationContainer;
import reportportal.enums.LoginEnum;
//import reportportal.enums.NavigationEnum;
import reportportal.enums.NavigationEnum;
import reportportal.pages.*;
import reportportal.utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.testng.AssertJUnit.assertTrue;
import static reportportal.drivers.BrowserFactory.getDriverInstance;
import static reportportal.utils.LoginConsumer.performLogin;
import static reportportal.utils.NavigationPredicates.performNavValidations;
//import static reportportal.utils.NavigationPredicates.performNavValidations;


public class RPSteps {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FiltersPage filtersPage;
    MembersPage membersPage;
    LaunchesPage launchesPage;
    SettingsPage settingsPage;
    NavigationContainer navigationContainer;

    public static final Logger logger = LogManager.getLogger();


    public RPSteps() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        navigationContainer = new NavigationContainer();
        filtersPage = new FiltersPage();
        membersPage = new MembersPage();
        launchesPage = new LaunchesPage();
        settingsPage = new SettingsPage();

    }

    @Given("I launch Report Portal")
    public void launchReportPortal() {
        driver = getDriverInstance();
        driver.get(System.getenv("LOCAL_HOST_URL"));
    }

    @And("I enter {string} for login")
    public void iEnterCredentials(String expectedFlow) {
        Consumer<LoginPage> loginConsumer = LoginEnum.valueOf(expectedFlow.toUpperCase()).getLoginConsumer();
        performLogin(loginConsumer, loginPage);
    }

    @But("I see the error {string}")
    public void theLoginAuthorizationErrorIsDisplayed(String errorMsg) {
        assertTrue(loginPage.getLoginErrorMessage(errorMsg));
    }

    @And("I validate {string} elements from Navigation Bar")
    @And("I navigate to {string} page from the Navigation Bar")
    public void iValidateNavContainerElements(String pane) {
        Predicate<NavigationContainer> navPredicates = NavigationEnum.valueOf(pane.toUpperCase()).getNavPredicates();
        performNavValidations(navPredicates, navigationContainer);
    }

    @And("I should be in {string} Page")
    public void validatePage(String pageName) {
        switch (pageName) {
            case "Login":
                loginPage.verify();
                break;
            case "Dashboard":
                dashboardPage.verify();
                break;
            case "Filters":
                filtersPage.verify();
            default:
                throw new IllegalArgumentException("Not landed on the correct page.");
        }
    }

    @When("I click Add Filters")
    public void clickAddFilters() {
        filtersPage.clickAddFilterButton();
    }

    @Then("I create and validate the creation of filters named in {string} sheet of {string} file")
    public void bulkCreateAndValidate(String sheetName, String fileName) throws IOException {
        List<Map<String, String>> filtersParams = ExcelReader.readCells(sheetName, fileName);
        assertTrue("Filters are not created", launchesPage.bulkCreateAndValidateFilters(filtersParams, filtersPage, navigationContainer));
    }

    @Then("I delete and validate the absence of filters named in {string} sheet of {string} file")
    public void bulkDeleteAndValidate(String sheetName, String fileName) throws IOException {
        List<Map<String, String>> filtersParams = ExcelReader.readCells(sheetName, fileName);
        assertTrue("Filters are not deleted", filtersPage.bulkDeleteAndValidateAbsence(filtersParams));
    }

    @Then("I edit and validate the change of filters named in {string} sheet of {string} file")
    public void bulkEditAndValidate(String sheetName, String fileName) throws IOException {
        List<Map<String, String>> filtersParams = ExcelReader.readCells(sheetName, fileName);
        System.out.println(filtersParams);
        assertTrue("Filters are not deleted", launchesPage.editFiltersInLaunchesPage(filtersParams,filtersPage,navigationContainer));
    }

}
