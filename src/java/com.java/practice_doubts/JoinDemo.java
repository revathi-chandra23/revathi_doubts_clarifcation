class JoinDemo extends Thread {
    public void run() {
        for(int i = 1; i <= 3; i++) {
            System.out.println("Child thread: " + i);
            try { Thread.sleep(500); } catch(Exception e) {}
        }
    }
}
public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        JoinDemo t1 = new JoinDemo();
        t1.start();
        t1.join(); // main waits until t1 finishes
        System.out.println("Main thread runs after child thread completes");
    }
}