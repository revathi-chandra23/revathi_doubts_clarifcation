import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX = 3;

    // Producer
    public synchronized void produce(int val) throws InterruptedException {
        while (queue.size() == MAX) { // use while, not if, to handle spurious wakeups
            System.out.println("Buffer full, Producer waiting...");
            wait(); // releases lock, sleeps
        }
        queue.add(val);
        System.out.println("Produced: " + val + ", Size: " + queue.size());
        notifyAll(); // wake up consumers
    }

    // Consumer
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty, Consumer waiting...");
            wait(); // releases lock, sleeps
        }
        int val = queue.remove();
        System.out.println("Consumed: " + val + ", Size: " + queue.size());
        notifyAll(); // wake up producers
        return val;
    }
}

public class WaitNotifyDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.produce(i);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {}
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.consume();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {}
        }, "Consumer");

        producer.start();
        consumer.start();
    }
}