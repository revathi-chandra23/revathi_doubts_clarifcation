parallel stream+ peak


import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo6 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Problem 1: peek() for debugging - see each step
        List<Integer> result = numbers.stream()
                .peek(n -> System.out.println("Original: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("After filter: " + n))
                .map(n -> n * n)
                .peek(n -> System.out.println("After map: " + n))
                .collect(Collectors.toList());
        System.out.println("Final: " + result);

        // Problem 2: parallelStream vs stream timing
        long start = System.currentTimeMillis();
        long count = numbers.parallelStream()
                .map(StreamDemo6::heavyOperation) // simulate slow task
                .count();
        long end = System.currentTimeMillis();
        System.out.println("Parallel took: " + (end - start) + "ms, Count: " + count);
    }
    
    static int heavyOperation(int n) {
        try { Thread.sleep(100); } catch (Exception e) {}
        return n * 2;
    }
}