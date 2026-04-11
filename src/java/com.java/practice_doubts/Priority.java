class PriorityDemo extends Thread {
    public void run() {
        System.out.println(getName() + " priority: " + getPriority()); // getPriority()
    }
}
public class PriorityExample {
    public static void main(String[] args) {
        PriorityDemo t1 = new PriorityDemo();
        PriorityDemo t2 = new PriorityDemo();
        
        t1.setName("Low");
        t2.setName("High");
        
        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.MAX_PRIORITY); // 10
        
        t1.start();
        t2.start();
    }
}