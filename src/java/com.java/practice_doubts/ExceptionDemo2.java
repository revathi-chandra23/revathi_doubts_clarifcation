import java.util.*;

public class ExceptionDemo2 {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter index to access 0-2: ");
            int index = sc.nextInt(); // InputMismatchException if you enter text
            System.out.println("Value: " + arr[index]); // ArrayIndexOutOfBoundsException

            String s = null;
            System.out.println(s.length()); // NullPointerException

        } catch (InputMismatchException e) {
            System.out.println("Please enter only integer");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of range. Valid: 0 to " + (arr.length - 1));

        } catch (NullPointerException e) {
            System.out.println("Null value found");

        } catch (Exception e) { // generic catch must be last
            System.out.println("Some other error: " + e);
        }

        sc.close();
    }
}