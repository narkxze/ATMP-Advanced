package principles.dependency_injection;

public class DieselEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Diesel engine started");
    }
}
