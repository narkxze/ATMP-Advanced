package dp.strategy;

public class CreditCard implements PaymentStrategy {
    private String name;
    private String cardNumber;

    public CreditCard(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount +" paid with credit/debit card");
    }
}
