package streams_examples;
import java.util.*;

public class FindLst {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "C++");

        String lastElement = list.stream()
                                  .reduce((first, second) -> second)
                                  .orElse("No elements found");

        System.out.println("Last element: " + lastElement);
    }
    
}
