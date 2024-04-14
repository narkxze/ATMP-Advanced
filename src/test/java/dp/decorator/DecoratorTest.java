package dp.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Coffee c = new SimpleCoffee();
        c = new WithMilk(c);
        c = new WithSugar(c);

        System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getDescription());
    }
}
