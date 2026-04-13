class DatabaseConnection {
    // volatile prevents reordering of constructor + assignment
    private static volatile DatabaseConnection instance;
    
    private String url;
    
    private DatabaseConnection() {
        // Simulate expensive work
        System.out.println("Creating DB connection...");
        try { Thread.sleep(1000); } catch (Exception e) {}
        this.url = "jdbc:mysql://localhost:3306/db";
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) { // 1st check, no lock
            synchronized (DatabaseConnection.class) {
                if (instance == null) { // 2nd check, with lock
                    instance = new DatabaseConnection(); // Without volatile, this can reorder
                }
            }
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("Connected to " + url);
    }
}

public class VolatileSingletonDemo {
    public static void main(String[] args) throws InterruptedException {
        // Start 10 threads to test thread safety
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DatabaseConnection db = DatabaseConnection.getInstance();
                db.connect();
            }, "Thread-" + i).start();
        }
    }
}