import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.TimeUnit;

public class ABADemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 1. ABA Problem with AtomicReference ===");
        AtomicReference<String> ar = new AtomicReference<>("A");

        Thread t1 = new Thread(() -> {
            String old = ar.get(); // reads A
            System.out.println("T1: read A, sleeping...");
            try { TimeUnit.SECONDS.sleep(2); } catch (Exception e) {}

            // T1 wakes up. Value is back to A, but it was B in between
            boolean success = ar.compareAndSet(old, "C");
            System.out.println("T1: CAS A->C success? " + success); // true, but we missed B
            System.out.println("T1: Final value: " + ar.get());
        });

        Thread t2 = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) {}
            ar.set("B");
            System.out.println("T2: Changed A -> B");
            ar.set("A");
            System.out.println("T2: Changed B -> A");
        });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("\n=== 2. Fixed with AtomicStampedReference ===");
        AtomicStampedReference<String> asr = new AtomicStampedReference<>("A", 0);

        Thread t3 = new Thread(() -> {
            int[] stampHolder = new int[1];
            String oldRef = asr.get(stampHolder); // read A, stamp 0
            int oldStamp = stampHolder[0];
            System.out.println("T3: read A, stamp=" + oldStamp + ", sleeping...");
            try { TimeUnit.SECONDS.sleep(2); } catch (Exception e) {}

            // Now try CAS: expect A + stamp 0, change to C + stamp 1
            boolean success = asr.compareAndSet(oldRef, "C", oldStamp, oldStamp + 1);
            System.out.println("T3: CAS A->C success? " + success); // false, stamp changed
            System.out.println("T3: Final: " + asr.getReference() + " stamp=" + asr.getStamp());
        });

        Thread t4 = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) {}
            int stamp = asr.getStamp();
            asr.compareAndSet("A", "B", stamp, stamp + 1); // A,0 -> B,1
            System.out.println("T4: Changed A->B, stamp=1");
            stamp = asr.getStamp();
            asr.compareAndSet("B", "A", stamp, stamp + 1); // B,1 -> A,2
            System.out.println("T4: Changed B->A, stamp=2");
        });

        t3.start(); t4.start();
        t3.join(); t4.join();
    }
}