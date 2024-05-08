package reportportal.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Filter {
    private List<Condition> conditions;
    private String description;
    private String name;
    private List<Order> orders;
    private String type;

    @Data
    @Builder
    public static class Condition {
        private String condition;
        private String filteringField;
        private String value;
    }

    @Data
    @Builder
    public static class Order {
        private boolean isAsc;
        private String sortingColumn;
    }
}
