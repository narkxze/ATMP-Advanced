package reportportal.pages;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;

import java.util.Map;
import java.util.function.Supplier;

public class PageFactory {
    private static final Supplier<BasePage> LOGIN_PAGE = LoginPage::new;
    private static final Map<String, Supplier<BasePage>> PAGES = Map.of("LOGIN", LOGIN_PAGE);

    public static BasePage getPageInstance(String pageName) {
        return PAGES.get(pageName).get();
    }
}
