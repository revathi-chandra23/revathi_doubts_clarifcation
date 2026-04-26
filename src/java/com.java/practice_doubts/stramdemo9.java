import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo9 {
    public static void main(String[] args) {
        List<Integer> sortedSalaries = Arrays.asList(30000, 45000, 50000, 55000, 60000, 75000, 90000);

        // Problem 1: takeWhile - get salaries < 60000. Stops at first failure
        List<Integer> lowSalaries = sortedSalaries.stream()
                .takeWhile(sal -> sal < 60000)
                .collect(Collectors.toList());
        System.out.println("Salaries < 60k: " + lowSalaries);

        // Problem 2: dropWhile - skip salaries < 60000, take rest
        List<Integer> highSalaries = sortedSalaries.stream()
                .dropWhile(sal -> sal < 60000)
                .collect(Collectors.toList());
        System.out.println("Salaries >= 60k: " + highSalaries);

        // Problem 3: limit + skip for pagination
        int page = 2, size = 2; // page 2, 2 items per page
        List<Integer> pageData = sortedSalaries.stream()
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
        System.out.println("Page 2 data: " + pageData);

        // Problem 4: findFirst vs findAny in parallel
        Optional<Integer> first = sortedSalaries.parallelStream()
                .filter(s -> s > 50000)
                .findFirst(); // always 55000
        Optional<Integer> any = sortedSalaries.parallelStream()
                .filter(s -> s > 50000)
                .findAny(); // could be any match
        System.out.println("findFirst: " + first + ", findAny: " + any);
    }
}