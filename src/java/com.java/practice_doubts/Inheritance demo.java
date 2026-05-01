class Employee { // Parent class
    String name;
    int id;
    double baseSalary;
    
    Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }
    
    void displayDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Base: " + baseSalary);
    }
    
    double calculateSalary() {
        return baseSalary;
    }
}

class Developer extends Employee { // Child class
    int bonus;
    
    Developer(String name, int id, double baseSalary, int bonus) {
        super(name, id, baseSalary); // call parent constructor
        this.bonus = bonus;
    }
    
    @Override
    double calculateSalary() { // method overriding
        return baseSalary + bonus;
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Developer dev = new Developer("Revathi", 101, 80000, 20000);
        dev.displayDetails(); // inherited method
        System.out.println("Total Salary: " + dev.calculateSalary());
    }
}