class DeadlockDemo {
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public void method1() {
        synchronized (lockA) {
            System.out.println("T1: locked A");
            try { Thread.sleep(100); } catch (Exception e) {}
            synchronized (lockB) { // needs B but T2 has it
                System.out.println("T1: locked B");
            }
        }
    }

    public void method2() {
        synchronized (lockB) {
            System.out.println("T2: locked B");
            try { Thread.sleep(100); } catch (Exception e) {}
            synchronized (lockA) { // needs A but T1 has it
                System.out.println("T2: locked A");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockDemo d = new DeadlockDemo();
        new Thread(d::method1, "T1").start();
        new Thread(d::method2, "T2").start();
        // Program hangs. Use jstack to detect deadlock.
    }
}