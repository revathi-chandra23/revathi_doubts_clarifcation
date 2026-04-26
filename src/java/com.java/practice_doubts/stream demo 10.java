import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Transaction {
    String id; String type; double amount;
    Transaction(String id, String type, double amount) {
        this.id = id; this.type = type; this.amount = amount;
    }
    public String getType() { return type; }
    public double getAmount() { return amount; }
}

public class StreamDemo10 {
    public static void main(String[] args) {
        List<Transaction> txns = Arrays.asList(
            new Transaction("T1", "CREDIT", 1000),
            new Transaction("T2", "DEBIT", 200),
            new Transaction("T3", "CREDIT", 500),
            new Transaction("T4", "DEBIT", 300),
            new Transaction("T5", "CREDIT", 700)
        );

        // Problem 1: Calculate net balance using custom collector
        double netBalance = txns.stream().collect(
            Collector.of(
                () -> new double[1], // supplier: accumulator
                (acc, txn) -> acc[0] += "CREDIT".equals(txn.getType()) ? txn.getAmount() : -txn.getAmount(), // accumulator
                (acc1, acc2) -> { acc1[0] += acc2[0]; return acc1; }, // combiner
                acc -> acc[0] // finisher
            )
        );
        System.out.println("Net Balance: $" + netBalance);

        // Problem 2: IntStream for range + prime numbers
        List<Integer> primes = IntStream.rangeClosed(2, 50)
                .filter(StreamDemo10::isPrime)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Primes 2-50: " + primes);

        // Problem 3: Generate 5 random employee IDs using IntStream
        List<String> empIds = new Random().ints(5, 1000, 9999)
                .mapToObj(id -> "EMP" + id)
                .collect(Collectors.toList());
        System.out.println("Random Emp IDs: " + empIds);
    }
    
    static boolean isPrime(int n) {
        return n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n))
                .noneMatch(i -> n % i == 0);
    }
}