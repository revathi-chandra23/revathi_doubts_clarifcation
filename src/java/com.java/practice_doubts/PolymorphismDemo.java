class Calculator {
    // Compile-time polymorphism: Method Overloading
    int add(int a, int b) {
        return a + b;
    }
    
    double add(double a, double b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
}

class Animal {
    void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    // Runtime polymorphism: Method Overriding
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meows");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        // Compile-time
        Calculator calc = new Calculator();
        System.out.println("Add 2 ints: " + calc.add(5, 10));
        System.out.println("Add 2 doubles: " + calc.add(5.5, 10.5));
        
        // Runtime
        Animal a1 = new Dog(); // parent ref, child object
        Animal a2 = new Cat();
        a1.makeSound(); // Dog barks
        a2.makeSound(); // Cat meows
    }
}