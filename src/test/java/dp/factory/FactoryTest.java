package dp.factory;

public class FactoryTest {
    public static void main(String[] args) {
        PizzaFactory pizzaFactory = new PizzaFactory();
        Pizza cheesePizza = pizzaFactory.createPizza("Cheese");
        cheesePizza.addIngredients();
        cheesePizza.bakePizza();
        Pizza veggiePizza = pizzaFactory.createPizza("Veggie");
        veggiePizza.addIngredients();
        veggiePizza.bakePizza();
    }
}