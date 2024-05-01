package principles.lsp;

import java.util.List;

public class LSPTest {

    public static void main(String[] args) {
        Bird ostrich = new Ostrich();
        Bird eagle = new Eagle();
        List<Bird> birds = List.of(ostrich, eagle);
        birds.forEach(Bird::travel);
    }
}
