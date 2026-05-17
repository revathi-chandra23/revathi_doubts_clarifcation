package streams_examples;

public class FindFirst {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "C++");

        String firstElement = list.stream()
                                  .findFirst()
                                  .orElse("No elements found");

        System.out.println("First element: " + firstElement);
    }
}
