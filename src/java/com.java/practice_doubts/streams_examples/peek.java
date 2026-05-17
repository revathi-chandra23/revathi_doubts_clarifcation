package streams_examples;

public class peek {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "C++");

        list.stream()
            .peek(System.out::println) // This will print each element as it is processed
            .filter(s -> s.length() > 4) // Filter elements with length greater than 4
            .forEach(System.out::println); // This will print the filtered elements
    }
    
}
