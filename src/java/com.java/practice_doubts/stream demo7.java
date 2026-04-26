import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id; String name; double salary; String department;
    Employee(int id, String name, double salary, String department) {
        this.id = id; this.name = name; this.salary = salary; this.department = department;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
}

public class StreamDemo7 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Revathi", 75000, "IT"),
            new Employee(2, "Anil", 45000, "HR"),
            new Employee(3, "Priya", 90000, "IT"),
            new Employee(4, "Kiran", 90000, "Finance") // duplicate salary
        );

        // Problem 1: toMap - id as key, name as value
        Map<Integer, String> idToName = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getName));
        System.out.println("ID -> Name: " + idToName);

        // Problem 2: toMap with duplicate keys - keep highest salary
        Map<String, Employee> deptToHighestPaid = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        e -> e,
                        (e1, e2) -> e1.getSalary() >= e2.getSalary() ? e1 : e2
                ));
        deptToHighestPaid.forEach((dept, emp) -> 
            System.out.println(dept + " top earner: " + emp.getName() + " $" + emp.getSalary()));

        // Problem 3: Join names dept-wise
        Map<String, String> deptToNames = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.joining(", "))
                ));
        System.out.println("Dept names: " + deptToNames);
    }
}