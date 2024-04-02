package reportportal.steps;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import reportportal.containers.NavigationContainer;
import reportportal.enums.LoginEnum;
import reportportal.enums.NavigationEnum;
import reportportal.pages.BasePage;
import reportportal.pages.DashboardPage;
import reportportal.pages.LoginPage;
import reportportal.pages.PageFactory;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.testng.AssertJUnit.assertTrue;
import static reportportal.drivers.BrowserFactory.getDriverInstance;
import static reportportal.pages.PageFactory.getPageInstance;
import static reportportal.utils.LoginConsumer.performLogin;
import static reportportal.utils.NavigationPredicates.performNavValidations;


public class RPSteps {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    NavigationContainer navigationContainer;

    public static final Logger logger = LogManager.getLogger();


//    public RPSteps() {
//        loginPage = (LoginPage) PageFactory.getPageInstance("LOGIN");
//        dashboardPage = (DashboardPage) getPageInstance("DASHBOARD");
//        navigationContainer = (NavigationContainer) getPageInstance("NAVIGATION");
//
//    }

    @Given("I launch Report Portal")
    public void launchReportPortal() {
        driver = getDriverInstance();
        driver.get(System.getenv("LOCAL_HOST_URL"));
        logger.info(System.getenv("LOCAL_HOST_URL") + " is Launched");
    }

    @When("I validate the current page as {string} Page")
    public void validateLoginPage(String pageName) {
        BasePage page = getPageInstance(pageName);
        assertTrue(page.verify());
    }

    @And("I enter credentials for {string}")
    public void iEnterCredentials(String expectedFlow) {
        Consumer<LoginPage> loginConsumer = LoginEnum.valueOf(expectedFlow).getLoginConsumer();
        performLogin(loginConsumer, (LoginPage) getPageInstance("LOGIN"));
    }

    @But("The Login Authorization error {string} is displayed")
    public void theLoginAuthorizationErrorIsDisplayed(String errorMsg) {
        assertTrue(loginPage.getLoginErrorMessage(errorMsg));
    }

    @And("I validate {string} elements from Navigation Bar")
    public void iValidateNavContainerElements(String pane) {
        Predicate<NavigationContainer> navPredicates = NavigationEnum.valueOf(pane).getNavPredicates();
        performNavValidations(navPredicates, navigationContainer);
    }
}
