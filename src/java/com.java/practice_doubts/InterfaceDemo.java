interface PaymentGateway {
    void pay(double amount); // public abstract by default
    
    default void printReceipt() { // Java 8 default method
        System.out.println("Payment successful. Thank you!");
    }
    
    static void showSupportedBanks() { // static method in interface
        System.out.println("Supports: SBI, HDFC, ICICI");
    }
}

class PhonePe implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PhonePe UPI");
    }
}

class CreditCard implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        PaymentGateway p1 = new PhonePe();
        PaymentGateway p2 = new CreditCard();
        
        p1.pay(1500);
        p1.printReceipt();
        
        p2.pay(5000);
        p2.printReceipt();
        
        PaymentGateway.showSupportedBanks(); // call static method
    }
}