package reportportal.utils;

public class EnvironmentUtils {
    final static String envKeyPrefix = "envKey_";

    public static String getEnvironmentValue(String value) {
        if (!value.startsWith(envKeyPrefix)) {
            return System.getenv(value);
        }
        String envKey = value.replace(envKeyPrefix, "");
        return System.getenv(envKey);
    }
}
