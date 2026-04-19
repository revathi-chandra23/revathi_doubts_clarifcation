import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 3;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition(); // for producers
    private final Condition notEmpty = lock.newCondition(); // for consumers

    public void produce(int val) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY) {
                System.out.println("Buffer full, Producer waiting...");
                notFull.await(); // same as wait(), releases lock
            }
            queue.add(val);
            System.out.println("Produced: " + val + ", Size: " + queue.size());
            notEmpty.signal(); // wake up 1 consumer. Same as notify()
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("Buffer empty, Consumer waiting...");
                notEmpty.await();
            }
            int val = queue.remove();
            System.out.println("Consumed: " + val + ", Size: " + queue.size());
            notFull.signal(); // wake up 1 producer
            return val;
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.produce(i);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {}
        }, "Producer").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.consume();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {}
        }, "Consumer").start();
    }
}