import java.util.*;

@FunctionalInterface
interface Calculator {
    int operation(int a, int b);
}

public class LambdaDemo {
    public static void main(String[] args) {
        // Lambda: (parameters) -> expression
        Calculator add = (a, b) -> a + b;
        Calculator sub = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        
        System.out.println("10 + 5 = " + add.operation(10, 5));
        System.out.println("10 - 5 = " + sub.operation(10, 5));
        System.out.println("10 * 5 = " + multiply.operation(10, 5));
        
        // Lambda with Collections
        List<String> names = Arrays.asList("Revathi", "Anil", "Zoya");
        names.forEach(name -> System.out.println("Hello " + name));
        
        // Lambda for sorting
        names.sort((s1, s2) -> s1.length() - s2.length());
        System.out.println("Sorted by length: " + names);
    }
}