package reportportal.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import reportportal.drivers.BrowserFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import reportportal.enums.LoginEnum;
import reportportal.pages.BasePage;
import reportportal.pages.LoginPage;
import reportportal.pages.PageFactory;
import reportportal.utils.LoginConsumer;

import java.util.function.Consumer;

public class RPSteps {

    WebDriver driver;
    LoginPage loginPage;

    public RPSteps() {
        loginPage = new LoginPage();
    }

    @Given("I launch Report Portal in {string}")
    public void launchReportPortal(String browser) {
        driver = BrowserFactory.getDriverInstance(browser);
        driver.get("https://rp.epam.com");
    }

    @When("I validate the current page as {string} Page")
    public void validateLoginPage(String pageName) {
        BasePage page = PageFactory.getPageInstance(pageName);
        Assert.assertTrue(page.verify());
    }

    @And("I enter credentials for {string}")
    public void iEnterCredentials(String expectedFlow) throws InterruptedException {
        Consumer<LoginPage> loginConsumer = LoginEnum.valueOf(expectedFlow).getLoginConsumer();
        LoginConsumer.performLogin(loginConsumer, loginPage);
    }


    @But("The Login Authorization error {string} is displayed")
    public void theLoginAuthorizationErrorIsDisplayed(String errorMsg) {
        Assert.assertTrue(loginPage.getLoginErrorMessage(errorMsg));
    }
}
