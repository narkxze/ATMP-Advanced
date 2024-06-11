package principles.lsp;

public class Eagle implements Bird {
    @Override
    public void travel() {
        fly();
    }

    public void fly() {
        System.out.println("Eagle is flying");
    }
}


