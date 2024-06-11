package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reportportal.annotations.PageName;

@PageName("Members")
public class MembersPage extends BasePage {
    private final static By membersToolBar = By.xpath("//div[contains(@class,'membersPageToolbar__members-page-toolbar')]");
    @Override
    public boolean verify() {
       return isDisplayed(membersToolBar);
    }
}
