import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {
    private final Map<String, String> map = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    // Many threads can read at same time
    public String get(String key) {
        rwLock.readLock().lock(); // multiple readers allowed
        try {
            System.out.println(Thread.currentThread().getName() + " reading...");
            Thread.sleep(1000); // simulate slow read
            return map.get(key);
        } catch (InterruptedException e) {
            return null;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    // Only 1 thread can write, and no readers allowed during write
    public void put(String key, String value) {
        rwLock.writeLock().lock(); // exclusive lock
        try {
            System.out.println(Thread.currentThread().getName() + " writing...");
            Thread.sleep(2000); // simulate slow write
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " write done");
        } catch (InterruptedException e) {
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        cache.put("config", "v1"); // initial data

        // 3 reader threads - these will run parallel
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                String val = cache.get("config");
                System.out.println(Thread.currentThread().getName() + " got: " + val);
            }, "Reader-" + i).start();
        }

        // 1 writer thread - this will wait for all readers to finish
        new Thread(() -> {
            cache.put("config", "v2");
        }, "Writer").start();

        // 2 more readers - these will wait for writer to finish
        for (int i = 4; i <= 5; i++) {
            new Thread(() -> {
                String val = cache.get("config");
                System.out.println(Thread.currentThread().getName() + " got: " + val);
            }, "Reader-" + i).start();
        }
    }
}