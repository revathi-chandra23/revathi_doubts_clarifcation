class NumberThread extends Thread {
    String name;
    NumberThread(String name) { this.name = name; }
    
    public void run() {
        for(int i = 1; i <= 3; i++) {
            System.out.println(name + " prints " + i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new NumberThread("Thread-A").start();
        new NumberThread("Thread-B").start();
    }
}