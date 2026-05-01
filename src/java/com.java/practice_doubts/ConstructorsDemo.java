class Student {
    String name;
    int age;
    String course;
    
    // Default constructor
    Student() {
        this("Unknown", 0, "Not Assigned"); // constructor chaining
    }
    
    // Parameterized constructor
    Student(String name, int age) {
        this.name = name; // this = current object
        this.age = age;
        this.course = "Java Full Stack";
    }
    
    // Constructor overloading
    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }
    
    void display() {
        System.out.println("Name: " + name + ", Age: " + age + ", Course: " + course);
    }
}

public class ConstructorDemo {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student("Revathi", 24);
        Student s3 = new Student("Anil", 25, "Python");
        
        s1.display();
        s2.display();
        s3.display();
    }
}