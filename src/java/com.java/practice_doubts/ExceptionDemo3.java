import java.io.*;

public class ExceptionDemo3 {

    // Method declares it may throw IOException - checked exception
    static void checkFile(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            throw new IOException("File not found: " + fileName); // manually throwing
        }
        System.out.println("File exists: " + fileName);
    }

    public static void main(String[] args) {
        try {
            checkFile("test.txt"); // must handle or declare
        } catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("Main continues...");
    }
}