package reportportal.containers;

import org.openqa.selenium.By;
import reportportal.pages.BasePage;

public class NavigationContainer extends BasePage {
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


    public boolean isProjectDetailDisplayed() {
        click(projectSelector);
        return isDisplayed(projectNamesBlock);
    }

    public boolean isNavigatedToDashboard() {
        click(dashBoardSelector);
        return getDriver().getCurrentUrl().contains("dashboard");
    }

    public boolean isNavigatedToLauncher() {
        click(launchesSelector);
        return getDriver().getCurrentUrl().contains("launches");
    }

    //
    @Override
    public boolean verify() {
        return true;
    }

    public boolean isNavigatedToFilter() {
        click(filtersSelector);
        return getDriver().getCurrentUrl().contains("filters");
    }

    public boolean isNavigatedToUserDebug() {
        click(debugSelector);
        return getDriver().getCurrentUrl().contains("userdebug");
    }

    public boolean isNavigatedToMembers() {
        click(memberSelector);
        return getDriver().getCurrentUrl().contains("members");
    }

    public boolean isNavigatedToSettings() {
        click(settingsSelector);
        return getDriver().getCurrentUrl().contains("settings");
    }

    public boolean isUserBlockDisplayed() {
        waitUntilInvisible(successfulSignInToast);
        click(userBlock);
        return isDisplayed(logoutUser);
    }
}
//}
