package principles.dependency_injection;

public class DITest {
    public static void main(String[] args) {
        Engine petrolEngine = new PetrolEngine();
        Car petrolCar = new Car(petrolEngine);
        petrolCar.start();

        Engine dieselEngine = new DieselEngine();
        Car dieselCar = new Car(dieselEngine);
        dieselCar.start();

    }
}
