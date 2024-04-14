package reportportal.utils;

import reportportal.pages.*;

import java.util.function.Predicate;

public class PageValidationPredicates {
    public static final Predicate<LoginPage> LOGIN_PAGE_PREDICATE = LoginPage::verify;
    public static final Predicate<DashboardPage> DASHBOARD_PAGE_PREDICATE = DashboardPage::verify;
    public static final Predicate<FiltersPage> FILTERS_PAGE_PREDICATE = FiltersPage::verify;
    public static final Predicate<MembersPage> MEMBERS_PAGE_PREDICATE = MembersPage::verify;
    public static final Predicate<DebugPage> DEBUG_PAGE_PREDICATE = DebugPage::verify;
    public static final Predicate<SettingsPage> SETTINGS_PAGE_PREDICATE = SettingsPage::verify;
    public static final Predicate<LaunchesPage> LAUNCHES_PAGE_PREDICATE = LaunchesPage::verify;


    public void isPageOpened(Predicate<BasePage> pagePredicate, BasePage page) {
        pagePredicate.test(page);
    }


}
