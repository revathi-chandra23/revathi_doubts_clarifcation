public class HoldsLocksInThreads {
    static Object lock = new Object();
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 holds lock? " + Thread.holdsLock(lock)); // holdsLock()
                System.out.println("Stack trace:");
                for (StackTraceElement e : Thread.currentThread().getStackTrace()) { // getStackTrace()
                    System.out.println(e);
                }
            }
        }, "Demo-Thread");
        
        t1.start();
        System.out.println("Main holds lock? " + Thread.holdsLock(lock)); // false
    }
}