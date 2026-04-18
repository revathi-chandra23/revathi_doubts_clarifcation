class Printer {
    static int copies = 0;

    // This locks Printer.class. Only 1 thread across ALL objects
    static synchronized void printStatic(String doc) {
        copies++;
        System.out.println(Thread.currentThread().getName() + " printed " + doc + " | Total: " + copies);
    }

    // This locks 'this' object. Different objects can run parallel
    synchronized void printInstance(String doc) {
        System.out.println(Thread.currentThread().getName() + " instance print " + doc);
    }
}

public class StaticSyncDemo {
    public static void main(String[] args) {
        Printer p1 = new Printer();
        Printer p2 = new Printer();

        new Thread(() -> Printer.printStatic("PDF")).start(); // locks class
        new Thread(() -> Printer.printStatic("Word")).start(); // waits for class lock

        new Thread(() -> p1.printInstance("Excel")).start(); // locks p1, runs parallel
        new Thread(() -> p2.printInstance("PPT")).start(); // locks p2, runs parallel
    }
}