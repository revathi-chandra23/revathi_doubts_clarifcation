import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

class Student {
    String name;
    int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}

public class StudentStreamExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("John", 85),
                new Student("Alice", 90),
                new Student("Bob", 78),
                new Student("Eve", 92)
        );

        // Calculate average marks
        OptionalDouble averageMarks = students.stream()
                .mapToInt(Student::getMarks)
                .average();
        System.out.println("Average marks: " + averageMarks.orElse(Double.NaN));

        // Find students with marks > 85
        List<Student> topStudents = students.stream()
                .filter(s -> s.getMarks() > 85)
                .collect(Collectors.toList());
        System.out.println("Top students: " + topStudents.stream().map(Student::getName).collect(Collectors.toList()));

        // Find the student with highest marks
        Student topper = students.stream()
                .max((s1, s2) -> Integer.compare(s1.getMarks(), s2.getMarks()))
                .orElse(null);
        System.out.println("Topper: " + topper.getName());

        // Count students with marks >= 80
        long count = students.stream()
                .filter(s -> s.getMarks() >= 80)
                .count();
        System.out.println("Students with marks >= 80: " + count);
    }
}