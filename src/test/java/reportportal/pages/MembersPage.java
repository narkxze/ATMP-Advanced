package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MembersPage extends BasePage {
    private static ThreadLocal<MembersPage> INSTANCE = null;
    private final static By membersToolBar = By.xpath("//div[contains(@class,'membersPageToolbar__members-page-toolbar')]");

    public static MembersPage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadLocal<>();
            INSTANCE.set(new MembersPage());
        }
        return INSTANCE.get();
    }

    @Override
    public boolean verify() {
       return isDisplayed(membersToolBar);
    }
}
