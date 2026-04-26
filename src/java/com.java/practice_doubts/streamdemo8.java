import java.util.*;
import java.util.stream.Collectors;

class Department {
    String name;
    List<String> projects;
    Department(String name, List<String> projects) {
        this.name = name; this.projects = projects;
    }
    public String getName() { return name; }
    public List<String> getProjects() { return projects; }
}

class Employee {
    String name;
    Optional<Department> dept; // dept can be null
    Employee(String name, Department dept) {
        this.name = name; this.dept = Optional.ofNullable(dept);
    }
    public String getName() { return name; }
    public Optional<Department> getDept() { return dept; }
}

public class StreamDemo8 {
    public static void main(String[] args) {
        Department it = new Department("IT", Arrays.asList("Banking", "E-commerce"));
        Department hr = new Department("HR", Arrays.asList("Recruitment"));
        
        List<Employee> employees = Arrays.asList(
            new Employee("Revathi", it),
            new Employee("Anil", hr),
            new Employee("Priya", it),
            new Employee("Kiran", null) // bench - no dept
        );

        // Problem 1: Get all unique projects across all employees
        List<String> allProjects = employees.stream()
                .map(Employee::getDept)
                .flatMap(Optional::stream) // Java 9+ : Optional to Stream
                .map(Department::getProjects)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("All projects: " + allProjects);

        // Problem 2: Find employees working on "Banking"
        List<String> bankingEmp = employees.stream()
                .filter(e -> e.getDept()
                        .map(Department::getProjects)
                        .orElse(Collections.emptyList())
                        .contains("Banking"))
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("Banking project team: " + bankingEmp);
    }
}