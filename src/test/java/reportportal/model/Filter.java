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
}
