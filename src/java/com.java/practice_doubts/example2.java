import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie");

        // Filter names starting with 'A' or 'B', then print
        names.stream()
             .filter(name -> name.startsWith("A") || name.startsWith("B"))
             .forEach(System.out::println);
    }
}