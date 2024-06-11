package principles.dependency_injection;

public class PetrolEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Petrol engine started");
    }
}
