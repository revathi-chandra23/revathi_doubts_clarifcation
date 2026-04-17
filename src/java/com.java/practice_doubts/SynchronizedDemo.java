class BankAccount {
    private int balance = 1000;

    // NOT SAFE
    void withdrawUnsafe(int amount) {
        if (balance >= amount) { // Thread A & B both pass
            try { Thread.sleep(10); } catch (Exception e) {} // simulate delay
            balance = balance - amount; // Both subtract, balance goes negative
        }
    }

    // SAFE
    synchronized void withdrawSafe(int amount) {
        if (balance >= amount) {
            try { Thread.sleep(10); } catch (Exception e) {}
            balance = balance - amount;
        }
    }

    synchronized int getBalance() { return balance; }
}

public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount acc = new BankAccount();
        Thread t1 = new Thread(() -> acc.withdrawUnsafe(1000), "T1");
        Thread t2 = new Thread(() -> acc.withdrawUnsafe(1000), "T2");
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Unsafe balance: " + acc.getBalance()); // Can be -1000

        BankAccount acc2 = new BankAccount();
        Thread t3 = new Thread(() -> acc2.withdrawSafe(1000), "T3");
        Thread t4 = new Thread(() -> acc2.withdrawSafe(1000), "T4");
        t3.start(); t4.start();
        t3.join(); t4.join();
        System.out.println("Safe balance: " + acc2.getBalance()); // 0 or 1000, never -1000
    }
}