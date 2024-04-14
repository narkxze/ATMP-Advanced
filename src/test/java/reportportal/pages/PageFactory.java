package reportportal.pages;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import reportportal.annotations.PageName;

public class PageFactory {
    private static final Map<String, BasePage> pages = new HashMap<>();

    public static BasePage getPage(String pageName) {
        if (!pages.containsKey(pageName)) {
            Reflections reflections = new Reflections("reportportal.pages");
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(PageName.class);
            annotated.stream()
                    .filter(clazz -> ((PageName) clazz.getAnnotation(PageName.class)).value().equals(pageName))
                    .forEach(clazz -> createPageInstance(clazz, pageName));
        }
        return pages.get(pageName);
    }

    private static void createPageInstance(Class<?> clazz, String pageName) {
        try {
            pages.put(pageName, (BasePage) clazz.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException("Could not instantiate page: " + pageName, e);
        }
    }
}
