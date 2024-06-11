package dp.strategy;

public class UPI implements PaymentStrategy {
    private String emailId;
    private String password;

    public UPI(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Paypal.");
    }
}
