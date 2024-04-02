package reportportal.pages;

import org.apache.poi.ss.formula.functions.Na;
import reportportal.containers.NavigationContainer;

import java.util.Map;
import java.util.function.Supplier;

public class PageFactory {
//    private static final Supplier<BasePage> LOGIN_PAGE = LoginPage::getInstance;
//    private static final Supplier<BasePage> DASHBOARD_PAGE = DashboardPage::getInstance;
//    private static final Supplier<BasePage> NAVIGATION_CONTAINER = NavigationContainer::getInstance;
//    private static final Supplier<BasePage> LAUNCHES_PAGE = LaunchesPage::getInstance;
//    private static final Supplier<BasePage> FILTERS_PAGE = FiltersPage::getInstance;
//    private static final Supplier<BasePage> DEBUG_PAGE = DebugPage::getInstance;
//    private static final Supplier<BasePage> MEMBERS_PAGE = MembersPage::getInstance;
//    private static final Supplier<BasePage> SETTINGS_PAGE = SettingsPage::getInstance;

    private static final BasePage LOGIN_PAGE = LoginPage.getInstance();
    private static final BasePage DASHBOARD_PAGE = DashboardPage.getInstance();
    private static final BasePage NAVIGATION_CONTAINER = NavigationContainer.getInstance();
    private static final BasePage LAUNCHES_PAGE = LaunchesPage.getInstance();
    private static final BasePage FILTERS_PAGE = FiltersPage.getInstance();
    private static final BasePage DEBUG_PAGE = DebugPage.getInstance();
    private static final BasePage MEMBERS_PAGE = MembersPage.getInstance();
    private static final BasePage SETTINGS_PAGE = SettingsPage.getInstance();

//    private static final Map<String, Supplier<BasePage>> PAGES = Map.of("LOGIN", LOGIN_PAGE, "DASHBOARD", DASHBOARD_PAGE,
//            "NAVIGATION", NAVIGATION_CONTAINER, "LAUNCHES", LAUNCHES_PAGE,
//            "FILTERS", FILTERS_PAGE, "DEBUG", DEBUG_PAGE,
//            "MEMBERS", MEMBERS_PAGE, "SETTINGS", SETTINGS_PAGE);

    private static ThreadLocal<Map<String, BasePage>> PAGES = null;


    public static BasePage getPageInstance(String pageName) {
        PAGES = new ThreadLocal<>();
        PAGES.set(Map.of("LOGIN", LOGIN_PAGE, "DASHBOARD", DASHBOARD_PAGE,
                "NAVIGATION", NAVIGATION_CONTAINER, "LAUNCHES", LAUNCHES_PAGE,
                "FILTERS", FILTERS_PAGE, "DEBUG", DEBUG_PAGE,
                "MEMBERS", MEMBERS_PAGE, "SETTINGS", SETTINGS_PAGE));
        return PAGES.get().get(pageName);
    }
}
