class Task extends Thread {
    volatile boolean running = true; // volatile keyword
    
    public void run() {
        System.out.println("Thread started");
        while (running) { // always reads latest value from main memory
            // busy loop
        }
        System.out.println("Thread stopped");
    }
    
    public void stopThread() {
        running = false; // immediately visible to other thread
    }
}

public class VolatileDemoCorrect {
    public static void main(String[] args) throws InterruptedException {
        Task t1 = new Task();
        t1.start();
        
        Thread.sleep(1000);
        System.out.println("Main: setting running = false");
        t1.stopThread();
        
        t1.join(); // Now this will finish correctly
        System.out.println("Main ends");
    }
}