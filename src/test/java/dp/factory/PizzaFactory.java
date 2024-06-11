package dp.factory;

public class PizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;
        switch (type) {
            case "Cheese":
                pizza = new CheesePizza();
                break;
            case "Veggie":
                pizza = new VeggiePizza();
                break;
        }
        return pizza;
    }
}