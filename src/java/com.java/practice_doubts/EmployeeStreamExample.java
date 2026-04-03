import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeStreamExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 25, 50000.0),
                new Employee("Alice", 30, 60000.0),
                new Employee("Bob", 35, 70000.0),
                new Employee("Eve", 20, 40000.0)
        );

        // Filter employees with salary > 50000
        List<Employee> highSalaryEmployees = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .collect(Collectors.toList());
        System.out.println("High salary employees: " + highSalaryEmployees.stream().map(Employee::getName).collect(Collectors.toList()));

        // Map employee names to uppercase
        List<String> upperCaseNames = employees.stream()
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperCaseNames);

        // Calculate total salary
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total salary: " + totalSalary);

        // Find employee with highest salary
        Employee highestPaidEmployee = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .orElse(null);
        System.out.println("Highest paid employee: " + highestPaidEmployee.getName());
    }
}