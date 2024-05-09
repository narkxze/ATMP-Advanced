package reportportal.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Condition {
    private String condition;
    private String filteringField;
    private String value;
}
