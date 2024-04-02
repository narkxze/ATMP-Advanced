package reportportal.utils;

import reportportal.containers.NavigationContainer;

import java.util.function.Predicate;

public class NavigationPredicates {
    public static final Predicate<NavigationContainer> TOP_PANE_VALIDATION = NavigationContainer::validateProjectSelector;
    private static final Predicate<NavigationContainer> DASHBOARD_SELECTOR = NavigationContainer::validateDashBoardSelector;
    private static final Predicate<NavigationContainer> LAUNCHES_SELECTOR = NavigationContainer::validateLaunchesSelector;
    private static final Predicate<NavigationContainer> FILTERS_SELECTOR = NavigationContainer::validateFiltersSelector;
    private static final Predicate<NavigationContainer> DEBUG_SELECTOR = NavigationContainer::validateDebugSelector;
    private static final Predicate<NavigationContainer> MEMBERS_SELECTOR = NavigationContainer::validateMemberSelector;
    private static final Predicate<NavigationContainer> SETTINGS_SELECTOR = NavigationContainer::validateSettingSelector;
    private static final Predicate<NavigationContainer> SUPPORT_BLOCK = NavigationContainer::validateSupportBlock;
    private static final Predicate<NavigationContainer> USER_BLOCK = NavigationContainer::validateUserBlock;

    public static final Predicate<NavigationContainer> SIDE_PANE_VALIDATION =
            DASHBOARD_SELECTOR.and(LAUNCHES_SELECTOR)
                    .and(FILTERS_SELECTOR).and(DEBUG_SELECTOR)
                    .and(MEMBERS_SELECTOR).and(SETTINGS_SELECTOR);

    public static final Predicate<NavigationContainer> BOTTOM_PANE_VALIDATION =
//            SUPPORT_BLOCK.and(
                    USER_BLOCK;

    public static void performNavValidations(Predicate<NavigationContainer> navPredicate, NavigationContainer navigationContainer) {
        navPredicate.test(navigationContainer);
    }

}
