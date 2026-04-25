import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    double salary;
    String department;
    int age;
    String gender;

    Employee(int id, String name, double salary, String department, int age, String gender) {
        this.id = id; this.name = name; this.salary = salary;
        this.department = department; this.age = age; this.gender = gender;
    }
    
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getName() { return name; }
}

public class StreamDemo5 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Revathi", 75000, "IT", 25, "FEMALE"),
            new Employee(2, "Anil", 45000, "HR", 30, "MALE"),
            new Employee(3, "Priya", 90000, "IT", 28, "FEMALE"),
            new Employee(4, "Kiran", 60000, "Finance", 35, "MALE"),
            new Employee(5, "Sneha", 55000, "IT", 24, "FEMALE"),
            new Employee(6, "Ravi", 48000, "HR", 32, "MALE")
        );

        // Problem 1: Total salary using reduce()
        double totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.println("Total salary of company: $" + totalSalary);

        // Problem 2: Count employees per department
        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("Count by dept: " + countByDept);

        // Problem 3: Salary stats for IT department
        DoubleSummaryStatistics itStats = employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("IT Stats: " + itStats);
        // Gives count, sum, min, avg, max in one shot

        // Problem 4: Find oldest employee name using reduce
        String oldestEmpName = employees.stream()
                .reduce((e1, e2) -> e1.getAge() > e2.getAge() ? e1 : e2)
                .map(Employee::getName)
                .orElse("No employees");
        System.out.println("Oldest employee: " + oldestEmpName);

        // Problem 5: Dept with highest total salary
        Map.Entry<String, Double> highestPayingDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        System.out.println("Highest paying dept: " + highestPayingDept);
    }
}