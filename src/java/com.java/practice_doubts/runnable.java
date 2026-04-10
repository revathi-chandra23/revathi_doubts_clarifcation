public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            for(int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        };
        
        new Thread(task, "Alpha").start();
        new Thread(task, "Beta").start();
    }
}