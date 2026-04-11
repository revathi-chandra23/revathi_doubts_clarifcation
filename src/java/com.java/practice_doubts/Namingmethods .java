class NameDemo extends Thread {
    public void run() {
        System.out.println("Running thread name: " + getName()); // getName()
    }
}
public class NameExample {
    public static void main(String[] args) {
        NameDemo t1 = new NameDemo();
        t1.setName("Worker-Thread"); // setName()
        System.out.println("Name set to: " + t1.getName()); // getName()
        t1.start();
    }
}