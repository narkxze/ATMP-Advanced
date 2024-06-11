package principles.lsp;

public class Ostrich implements Bird {
    @Override
    public void travel() {
        run();
    }

    public void run() {
        System.out.println("Ostrich is running");
    }
}
