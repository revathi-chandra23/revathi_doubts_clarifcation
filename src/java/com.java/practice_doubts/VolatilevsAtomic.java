import java.util.concurrent.atomic.AtomicInteger;

class SharedCounter {
    volatile int volatileCount = 0; // NOT thread safe for ++
    AtomicInteger atomicCount = new AtomicInteger(0); // thread safe

    void incrementVolatile() {
        volatileCount++; // read-modify-write: not atomic
    }

    void incrementAtomic() {
        atomicCount.incrementAndGet(); // atomic operation
    }
}

public class VolatileVsAtomic {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter();
        int threads = 10;
        int increments = 10000;

        Thread[] arr = new Thread[threads];

        // Test volatile
        for (int i = 0; i < threads; i++) {
            arr[i] = new Thread(() -> {
                for (int j = 0; j < increments; j++) {
                    counter.incrementVolatile();
                }
            });
            arr[i].start();
        }
        for (Thread t : arr) t.join();

        System.out.println("Expected: " + (threads * increments));
        System.out.println("volatile result: " + counter.volatileCount); // Will be < 100000

        // Test AtomicInteger
        for (int i = 0; i < threads; i++) {
            arr[i] = new Thread(() -> {
                for (int j = 0; j < increments; j++) {
                    counter.incrementAtomic();
                }
            });
            arr[i].start();
        }
        for (Thread t : arr) t.join();

        System.out.println("AtomicInteger result: " + counter.atomicCount.get()); // Will be 100000
    }
}