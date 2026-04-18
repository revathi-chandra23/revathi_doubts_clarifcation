class SharedData {
    private int data;
    private boolean hasData = false; // flag: is data ready to consume?

    // Producer calls this
    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) { // while, not if - handles spurious wakeups
            wait(); // 1. Release lock 2. Sleep until notify
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + data);
        notify(); // Wake up 1 waiting consumer
    }

    // Consumer calls this  
    public synchronized int consume() throws InterruptedException {
        while (!hasData) { // while no data, wait
            wait(); // Release lock, sleep until producer notifies
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // Wake up producer
        return data;
    }
}

public class WaitNotifySmall {
    public static void main(String[] args) {
        SharedData data = new SharedData();

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    data.produce(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {}
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    data.consume();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {}
        });

        producer.start();
        consumer.start();
    }
}