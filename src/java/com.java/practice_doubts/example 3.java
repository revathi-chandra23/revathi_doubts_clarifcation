import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMethodsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Eve");

        // Filter
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Filtered names: " + filteredNames);

        // Map
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperCaseNames);

        // Reduce
        String concatenatedNames = names.stream()
                .reduce("", (a, b) -> a + ", " + b);
        System.out.println("Concatenated names: " + concatenatedNames);

        // Sort
        List<String> sortedNames = names.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted names: " + sortedNames);
    }
}