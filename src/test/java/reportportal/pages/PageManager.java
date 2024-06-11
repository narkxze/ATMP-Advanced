package reportportal.pages;

import reportportal.containers.NavigationContainer;

public class PageManager {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FiltersPage filtersPage;
    MembersPage membersPage;
    LaunchesPage launchesPage;
    SettingsPage settingsPage;
    NavigationContainer navigationContainer;
    DebugPage debugPage;

    public LoginPage getLoginPage() {
        if (loginPage == null)
            loginPage = new LoginPage();
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null)
            dashboardPage = new DashboardPage();
        return dashboardPage;
    }

    public FiltersPage getFiltersPage() {
        if (filtersPage == null)
            filtersPage = new FiltersPage();
        return filtersPage;
    }

    public MembersPage getMembersPage() {
        if (membersPage == null)
            membersPage = new MembersPage();
        return membersPage;
    }

    public LaunchesPage getLaunchesPage() {
        if (launchesPage == null)
            launchesPage = new LaunchesPage();
        return launchesPage;
    }

    public SettingsPage getSettingsPage() {
        if (settingsPage == null)
            settingsPage = new SettingsPage();
        return settingsPage;
    }

    public NavigationContainer getNavigationContainer() {
        if (navigationContainer == null)
            navigationContainer = new NavigationContainer();
        return navigationContainer;
    }

    public BasePage getUserDebugPage() {
        if (debugPage == null)
            debugPage = new DebugPage();
        return debugPage;
    }
}
