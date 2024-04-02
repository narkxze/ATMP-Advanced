package reportportal.utils;

import reportportal.containers.NavigationContainer;

import java.util.function.Predicate;

public class NavigationPredicates {
    public static final Predicate<NavigationContainer> TOP_PANE_VALIDATION = NavigationContainer::isProjectDetailDisplayed;
    public static final Predicate<NavigationContainer> DASHBOARD_SELECTOR = NavigationContainer::isNavigatedToDashboard;
    public static final Predicate<NavigationContainer> LAUNCHES_SELECTOR = NavigationContainer::isNavigatedToLauncher;
    public static final Predicate<NavigationContainer> FILTERS_SELECTOR = NavigationContainer::isNavigatedToFilter;
    public static final Predicate<NavigationContainer> DEBUG_SELECTOR = NavigationContainer::isNavigatedToUserDebug;
    public static final Predicate<NavigationContainer> MEMBERS_SELECTOR = NavigationContainer::isNavigatedToMembers;
    public static final Predicate<NavigationContainer> SETTINGS_SELECTOR = NavigationContainer::isNavigatedToSettings;
    public static final Predicate<NavigationContainer> BOTTOM_PANE_VALIDATION = NavigationContainer::isUserBlockDisplayed;

    public static final Predicate<NavigationContainer> SIDE_PANE_VALIDATION =
            DASHBOARD_SELECTOR.and(LAUNCHES_SELECTOR)
                    .and(FILTERS_SELECTOR).and(DEBUG_SELECTOR)
                    .and(MEMBERS_SELECTOR).and(SETTINGS_SELECTOR);

    public static void performNavValidations(Predicate<NavigationContainer> navPredicate, NavigationContainer navigationContainer) {
        navPredicate.test(navigationContainer);
    }

}
