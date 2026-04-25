import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo3 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Revathi", 75000, "IT", 25, "FEMALE"),
            new Employee(2, "Anil", 45000, "HR", 30, "MALE"),
            new Employee(3, "Priya", 90000, "IT", 28, "FEMALE"),
            new Employee(4, "Kiran", 90000, "Finance", 35, "MALE"),
            new Employee(5, "Sneha", 55000, "IT", 24, "FEMALE")
        );

        // Problem 1: Second highest salary
        Optional<Double> secondHighest = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
                
        System.out.println("Second highest salary: $" + secondHighest.orElse(0.0));

        // Problem 2: Average salary of females
        double avgFemaleSalary = employees.stream()
                .filter(e -> "FEMALE".equals(e.getGender()))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
                
        System.out.println("Average female salary: $" + avgFemaleSalary);
        
        // Output:
        // Second highest salary: $75000.0
        // Average female salary: $73333.33...
    }
}