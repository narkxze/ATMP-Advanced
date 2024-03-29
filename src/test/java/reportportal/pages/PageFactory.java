package reportportal.pages;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import reportportal.containers.NavigationContainer;

import java.util.Map;
import java.util.function.Supplier;

public class PageFactory {
    private static final Supplier<BasePage> LOGIN_PAGE = LoginPage::new;
    private static final Supplier<BasePage> DASHBOARD_PAGE = DashboardPage::new;
    private static final Supplier<BasePage> NAVIGATION_CONTAINER = NavigationContainer::new;
    private static final Supplier<BasePage> LAUNCHES_PAGE = LaunchesPage::new;
    private static final Supplier<BasePage> FILTERS_PAGE = FiltersPage::new;
    private static final Supplier<BasePage> DEBUG_PAGE = DebugPage::new;
    private static final Supplier<BasePage> MEMBERS_PAGE = MembersPage::new;
    private static final Supplier<BasePage> SETTINGS_PAGE = SettingsPage::new;

    private static final Map<String, Supplier<BasePage>> PAGES = Map.of("LOGIN", LOGIN_PAGE, "DASHBOARD", DASHBOARD_PAGE,
            "NAVIGATION", NAVIGATION_CONTAINER, "LAUNCHES", LAUNCHES_PAGE,
            "FILTERS", FILTERS_PAGE, "DEBUG", DEBUG_PAGE,
            "MEMBERS", MEMBERS_PAGE, "SETTINGS", SETTINGS_PAGE);

    public static BasePage getPageInstance(String pageName) {
        return PAGES.get(pageName).get();
    }
}
