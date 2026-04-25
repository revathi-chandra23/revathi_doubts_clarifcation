import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo2 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Revathi", 75000, "IT", 25, "FEMALE"),
            new Employee(2, "Anil", 45000, "HR", 30, "MALE"),
            new Employee(3, "Priya", 90000, "IT", 28, "FEMALE"),
            new Employee(4, "Kiran", 60000, "Finance", 35, "MALE"),
            new Employee(5, "Sneha", 55000, "IT", 24, "FEMALE"),
            new Employee(6, "Ravi", 48000, "HR", 32, "MALE")
        );

        // Problem: Highest paid employee in each department
        Map<String, Optional<Employee>> highestPaidByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))
                ));

        highestPaidByDept.forEach((dept, emp) -> 
            System.out.println(dept + " : " + emp.get().getName() + " $" + emp.get().getSalary())
        );
        
        // Output:
        // HR : Ravi $48000.0
        // Finance : Kiran $60000.0  
        // IT : Priya $90000.0
    }
}