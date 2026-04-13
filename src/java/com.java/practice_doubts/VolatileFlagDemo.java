class Worker extends Thread {
    private volatile boolean shutdown = false; // volatile flag

    public void run() {
        System.out.println("Worker started");
        while (!shutdown) { // reads latest value every loop
            // do work...
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        System.out.println("Worker stopped gracefully");
    }

    public void shutdown() {
        shutdown = true; // visible to worker thread immediately
    }
}

public class VolatileFlagDemo {
    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        w.start();

        Thread.sleep(2000);
        System.out.println("Main: requesting shutdown");
        w.shutdown(); // worker will see this and exit loop

        w.join();
        System.out.println("Main ends");
    }
}