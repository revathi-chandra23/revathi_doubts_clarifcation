// Payment strategy interface
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete payment strategies
class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public CreditCardStrategy(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using credit/debit card");
    }
}

class PayPalStrategy implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using PayPal");
    }
}

// Context
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay using credit card
        context.setPaymentStrategy(new CreditCardStrategy("1234-5678-9012-3456", "123", "12/25"));
        context.executePayment(100);

        // Pay using PayPal
        context.setPaymentStrategy(new PayPalStrategy("example@example.com", "password"));
        context.executePayment(200);
    }
}