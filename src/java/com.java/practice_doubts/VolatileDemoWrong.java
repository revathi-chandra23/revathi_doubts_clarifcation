class Task extends Thread {
    boolean running = true; // NOT volatile
    
    public void run() {
        System.out.println("Thread started");
        while (running) {
            // busy loop
        }
        System.out.println("Thread stopped");
    }
    
    public void stopThread() {
        running = false;
    }
}

public class VolatileDemoWrong {
    public static void main(String[] args) throws InterruptedException {
        Task t1 = new Task();
        t1.start();
        
        Thread.sleep(1000);
        System.out.println("Main: setting running = false");
        t1.stopThread(); // main thread changes it
        
        t1.join();
        // This might hang forever on some JVMs
    }
}