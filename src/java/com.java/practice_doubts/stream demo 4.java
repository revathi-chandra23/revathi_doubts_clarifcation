import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo4 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Revathi", 75000, "IT", 25, "FEMALE"),
            new Employee(2, "Anil", 45000, "HR", 30, "MALE"),
            new Employee(3, "Priya", 90000, "IT", 28, "FEMALE"),
            new Employee(4, "Kiran", 60000, "Finance", 35, "MALE")
        );

        // Problem 1: Partition by age > 30
        Map<Boolean, List<Employee>> partitioned = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 30));
        System.out.println("Age > 30: " + partitioned.get(true));
        System.out.println("Age <= 30: " + partitioned.get(false));

        // Problem 2: Comma-separated IT names sorted by age
        String itNames = employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .sorted(Comparator.comparing(Employee::getAge))
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println("IT employees by age: " + itNames);

        // Problem 3: FlatMap example
        List<List<String>> skills = Arrays.asList(
                Arrays.asList("Java", "Spring"),
                Arrays.asList("SQL", "Kafka"),
                Arrays.asList("Docker", "Java")
        );
        List<String> allSkills = skills.stream()
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("All unique skills: " + allSkills);
    }
}