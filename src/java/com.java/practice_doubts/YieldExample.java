class YieldDemo extends Thread {
    public YieldDemo(String name) {
        super(name);
    }
    
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println(getName() + " prints " + i);
            Thread.yield(); // hint: let other thread run
        }
    }
}

public class YieldExample {
    public static void main(String[] args) {
        YieldDemo t1 = new YieldDemo("Thread-A");
        YieldDemo t2 = new YieldDemo("Thread-B");
        
        t1.start();
        t2.start(); // both will yield, so output will likely interleave more
    }
}