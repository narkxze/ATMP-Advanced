package reportportal.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Order {
    private boolean isAsc;
    private String sortingColumn;
}
