import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // same as synchronized start
        try {
            count++; // critical section
        } finally {
            lock.unlock(); // MUST unlock, else deadlock
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockBasic {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread[] t = new Thread[10];

        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) c.increment();
            });
            t[i].start();
        }
        for (Thread thread : t) thread.join();
        System.out.println("Count: " + c.getCount()); // 10000
    }
}