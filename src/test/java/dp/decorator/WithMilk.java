package dp.decorator;

public class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee c) {
        super(c);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 5;
    }
}
