package reportportal.enums;

import reportportal.containers.NavigationContainer;

import java.util.function.Predicate;

import static reportportal.utils.NavigationPredicates.*;

public enum NavigationEnum {

    TOP_PANE(TOP_PANE_VALIDATION),
    SIDE_PANE(SIDE_PANE_VALIDATION),
    BOTTOM_PANE(BOTTOM_PANE_VALIDATION),
    FILTERS(FILTERS_SELECTOR),
    LAUNCHES(LAUNCHES_SELECTOR);


    private final Predicate<NavigationContainer> navPredicates;

    NavigationEnum(Predicate<NavigationContainer> navPredicates) {
        this.navPredicates = navPredicates;
    }

    public Predicate<NavigationContainer> getNavPredicates() {
        return navPredicates;
    }
}
