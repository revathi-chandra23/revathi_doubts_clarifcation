class InterruptDemo extends Thread {
    public void run() {
        try {
            for(int i = 1; i <= 5; i++) {
                System.out.println(getName() + " working: " + i);
                Thread.sleep(1000); // thread can be interrupted here
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted while sleeping");
        }
        System.out.println(getName() + " finished");
    }
}

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo t1 = new InterruptDemo();
        t1.setName("Worker");
        t1.start();
        
        Thread.sleep(2500); // let t1 run for 2.5 sec
        System.out.println("Main thread interrupting Worker");
        t1.interrupt(); // interrupt() called here
        
        t1.join();
        System.out.println("Main ends");
    }
}