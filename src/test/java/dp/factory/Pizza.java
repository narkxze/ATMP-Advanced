package dp.factory;

public abstract class Pizza {
    public abstract void addIngredients();

    public void bakePizza() {
        System.out.println("Pizza baked for 20 minutes.");
    }
}
