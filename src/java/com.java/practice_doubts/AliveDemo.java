class AliveDemo extends Thread {
    public void run() {
        try { Thread.sleep(1000); } catch(Exception e) {}
    }
}
public class AliveExample {
    public static void main(String[] args) throws InterruptedException {
        AliveDemo t1 = new AliveDemo();
        System.out.println("Before start: " + t1.isAlive()); // false
        t1.start();
        System.out.println("After start: " + t1.isAlive()); // true
        t1.join();
        System.out.println("After completion: " + t1.isAlive()); // false
    }
}