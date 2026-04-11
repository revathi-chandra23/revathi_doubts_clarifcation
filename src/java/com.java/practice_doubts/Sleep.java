public class SleepExample {
    public static void main(String[] args) {
        System.out.println("Program starts");
        try {
            Thread.sleep(2000); // pause main thread for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Program resumes after 2 sec");
    }
}