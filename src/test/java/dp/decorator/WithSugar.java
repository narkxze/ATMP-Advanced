package dp.decorator;

public class WithSugar extends CoffeeDecorator {
    public WithSugar(Coffee c) {
        super(c);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Whip";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 2;
    }
}
