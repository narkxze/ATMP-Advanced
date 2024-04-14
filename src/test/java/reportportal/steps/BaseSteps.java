package reportportal.steps;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import reportportal.pages.BasePage;
import reportportal.pages.PageFactory;

import static reportportal.drivers.BrowserFactory.launchUrl;

@RequiredArgsConstructor
public class BaseSteps {
    final static String envKeyPrefix = "envKey_";
    BasePage currentPage;

    public void open(String url) {
        launchUrl(url);
    }

    public BasePage getPage(String pageName) {
        currentPage = PageFactory.getPage(pageName);
        return currentPage;
    }

    public boolean validatePage(String pageName) {
        return getPage(pageName).verify();

    }

    public void enterValue(String value, String field) {
        WebElement ele = currentPage.getElement(field);
        currentPage.enterValue(ele, value);
    }

    public void clickButton(String element) {
        WebElement ele = currentPage.getElement(element);
        currentPage.click(ele);
    }


}
