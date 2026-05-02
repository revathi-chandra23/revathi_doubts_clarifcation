import java.io.*;

public class ExceptionDemo5 {
    public static void main(String[] args) {

        // try-with-resources: auto closes BufferedReader & FileReader
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {

            String line;
            while ((line = br.readLine())!= null) {
                bw.write(line.toUpperCase());
                bw.newLine();
            }
            System.out.println("File copied successfully");

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }
        // br and bw auto closed here, even if exception occurs
    }
}