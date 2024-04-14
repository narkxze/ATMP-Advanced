package dp.strategy;

public class StrategyTest {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("1234", 10);
        Item item2 = new Item("5678", 40);

        cart.addItem(item1);
        cart.addItem(item2);

        //pay by upi
        cart.pay(new UPI("myemail@example.com", "mypwd"));

        //pay by credit card
        cart.pay(new CreditCard("NK", "1234567890"));
    }
}
