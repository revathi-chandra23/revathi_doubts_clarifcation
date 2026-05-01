class BankAccount {
    private String accHolderName; // private = encapsulated
    private double balance;
    
    // Constructor
    public BankAccount(String name, double initialBalance) {
        this.accHolderName = name;
        this.balance = initialBalance;
    }
    
    // Getter
    public double getBalance() {
        return balance;
    }
    
    // Setter with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("Revathi", 50000); // object
        acc1.deposit(10000);
        acc1.withdraw(5000);
        System.out.println("Final Balance: " + acc1.getBalance());
        // acc1.balance = 999999; // Error: balance is private
    }
}