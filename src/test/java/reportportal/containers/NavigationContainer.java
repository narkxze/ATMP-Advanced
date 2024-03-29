package reportportal.containers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reportportal.pages.BasePage;

import static reportportal.pages.PageFactory.getPageInstance;

public class NavigationContainer extends BasePage {

    private static ThreadLocal<NavigationContainer> INSTANCE = null;

    private final By projectSelector = By.xpath("//aside//div[contains(@class,'main-block')]/div");
    private final By dashBoardSelector = By.xpath("//aside//div[contains(@class,'sidebar-btn')]//a[contains(@href,'dashboard')]");
    private final By launchesSelector = By.xpath("//aside//div[contains(@class,'sidebar-btn')]//a[contains(@href,'launches')]");
    private final By filtersSelector = By.xpath("//aside//div[contains(@class,'sidebar-btn')]//a[contains(@href,'filters')]");
    private final By debugSelector = By.xpath("//aside//div[contains(@class,'sidebar-btn')]//a[contains(@href,'userdebug')]");
    private final By memberSelector = By.xpath("//aside//div[contains(@class,'sidebar-btn')]//a[contains(@href,'members')]");
    private final By settingsSelector = By.xpath("//aside//div[contains(@class,'sidebar-btn')]//a[contains(@href,'settings')]");
    private final By supportBlock = By.xpath("//span[contains(@class,'supportBlock')]");
    private final By userBlock = By.xpath("//div[contains(@class,'userBlock')]");
    private final By projectNamesBlock = By.xpath("//a[contains(@class,'projectSelector')]");
    private final By supportInstructions = By.xpath("//a[contains(text(),'Instruction')]");
    private final By logoutUser = By.xpath("//div[contains(text(),'Logout')]");
    private final By successfulSignInToast = By.xpath("//p[text()='Signed in successfully']");


    public static NavigationContainer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
        }
        return INSTANCE.get();
    }

    public boolean validateProjectSelector() {
        getDriver().findElement(projectSelector).click();
        return getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(projectNamesBlock)).isDisplayed();
    }

    public boolean validateDashBoardSelector() {
        getDriver().findElement(dashBoardSelector).click();
        return getPageInstance("DASHBOARD").verify();
    }

    public boolean validateLaunchesSelector() {
        getDriver().findElement(launchesSelector).click();
        return getPageInstance("LAUNCHES").verify();
    }


    @Override
    public boolean verify() {
        return true;
    }

    public boolean validateFiltersSelector() {
        getDriver().findElement(filtersSelector).click();
        return getPageInstance("FILTERS").verify();
    }

    public boolean validateDebugSelector() {
        getDriver().findElement(debugSelector).click();
        return getPageInstance("DEBUG").verify();
    }

    public boolean validateMemberSelector() {
        getDriver().findElement(memberSelector).click();
        return getPageInstance("MEMBERS").verify();
    }

    public boolean validateSettingSelector() {
        getDriver().findElement(settingsSelector).click();
        return getPageInstance("SETTINGS").verify();
    }

    public boolean validateSupportBlock() {
        getDriver().findElement(supportBlock).click();
        return getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(supportInstructions)).isDisplayed();
    }

    public boolean validateUserBlock() {
        getExplicitWait().until(ExpectedConditions.invisibilityOfElementLocated(successfulSignInToast));
        getExplicitWait().until(ExpectedConditions.elementToBeClickable(userBlock)).click();
        return getExplicitWait().until(ExpectedConditions.presenceOfElementLocated(logoutUser)).isDisplayed();
    }
}
