import java.util.*;

class Bank {
    private final Map<String, Integer> accounts = new HashMap<>();
    private final Object lockA = new Object(); // lock for account operations
    private final Object lockB = new Object(); // lock for audit logs
    private final List<String> auditLog = new ArrayList<>();

    public void deposit(String user, int amt) {
        synchronized (lockA) { // only locks account map
            accounts.put(user, accounts.getOrDefault(user, 0) + amt);
        }
        synchronized (lockB) { // audit can happen parallel to other deposits
            auditLog.add("Deposit " + amt + " to " + user);
        }
    }

    public int getBalance(String user) {
        synchronized (lockA) {
            return accounts.getOrDefault(user, 0);
        }
    }
}

public class FineGrainedLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Thread t1 = new Thread(() -> bank.deposit("Alice", 100));
        Thread t2 = new Thread(() -> bank.deposit("Bob", 200));
        t1.start(); t2.start(); // These can run mostly parallel
        t1.join(); t2.join();
        System.out.println("Alice: " + bank.getBalance("Alice")); // 100
        System.out.println("Bob: " + bank.getBalance("Bob")); // 200
    }
}