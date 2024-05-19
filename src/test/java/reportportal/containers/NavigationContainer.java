package reportportal.containers;

import org.openqa.selenium.By;
import reportportal.pages.BasePage;
import reportportal.pages.PageManager;

import static reportportal.pages.PageFactory.getPage;

public class NavigationContainer extends BasePage {
    PageManager pageManager;
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
        return getPage("Dashboard").verify();
    }

    public boolean isNavigatedToLauncher() {
        click(launchesSelector);
        return getPage("Launches").verify();
    }

    //
    @Override
    public boolean verify() {
        return true;
    }

    public boolean isNavigatedToFilter() {
        click(filtersSelector);
        return getPage("Filters").verify();
    }

    public boolean isNavigatedToUserDebug() {
        click(debugSelector);
        return getPage("Debug").verify();
    }

    public boolean isNavigatedToMembers() {
        click(memberSelector);
        return getPage("Members").verify();
    }

    public boolean isNavigatedToSettings() {
        click(settingsSelector);
        return getPage("Settings").verify();
    }

    public boolean isUserBlockDisplayed() {
        waitUntilInvisible(successfulSignInToast);
        click(userBlock);
        return isDisplayed(logoutUser);
    }
}
//}
