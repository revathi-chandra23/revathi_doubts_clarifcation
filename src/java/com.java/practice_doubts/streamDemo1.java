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
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    
    @Override
    public String toString() {
        return name + " - " + department + " - $" + salary;
    }
}

public class StreamDemo1 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Revathi", 75000, "IT", 25, "FEMALE"),
            new Employee(2, "Anil", 45000, "HR", 30, "MALE"),
            new Employee(3, "Priya", 90000, "IT", 28, "FEMALE"),
            new Employee(4, "Kiran", 60000, "Finance", 35, "MALE"),
            new Employee(5, "Sneha", 55000, "IT", 24, "FEMALE")
        );

        // Problem: Get names of IT employees with salary > 50000
        List<String> result = employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .filter(e -> e.getSalary() > 50000)
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println("IT employees with >50k: " + result);
        // Output: IT employees with >50k: [Revathi, Priya, Sneha]
    }
}