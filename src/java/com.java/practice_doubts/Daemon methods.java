class DaemonDemo extends Thread {
    public void run() {
        while(!Thread.currentThread().isInterrupted()) { // isInterrupted()
            System.out.println(getName() + " running... State: " + getState()); // getState()
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted in sleep");
                break;
            }
        }
        System.out.println(getName() + " stopping");
    }
}

public class ThreadMethodsLeft {
    public static void main(String[] args) throws InterruptedException {
        DaemonDemo t1 = new DaemonDemo();
        t1.setName("Background-Task");
        t1.setDaemon(true); // setDaemon() - JVM can exit even if this runs
        System.out.println("Is Daemon? " + t1.isDaemon()); // isDaemon()
        System.out.println("State before start: " + t1.getState()); // getState()
        
        t1.start();
        System.out.println("State after start: " + t1.getState());
        
        Thread.sleep(2000);
        System.out.println("Active threads: " + Thread.activeCount()); // activeCount()
        
        t1.interrupt(); // signal it to stop
        t1.join();
        System.out.println("Final state: " + t1.getState()); // TERMINATED
    }
}