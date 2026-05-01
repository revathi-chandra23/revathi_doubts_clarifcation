abstract class Shape { // abstract class
    String color;
    
    Shape(String color) {
        this.color = color;
    }
    
    abstract double area(); // abstract method - no body
    
    void displayColor() { // concrete method
        System.out.println("Color: " + color);
    }
}

class Circle extends Shape {
    double radius;
    
    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double length, width;
    
    Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }
    
    @Override
    double area() {
        return length * width;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        // Shape s = new Shape(); // Error: cannot instantiate abstract class
        Shape s1 = new Circle("Red", 5);
        Shape s2 = new Rectangle("Blue", 4, 6);
        
        s1.displayColor();
        System.out.println("Circle Area: " + s1.area());
        
        s2.displayColor();
        System.out.println("Rectangle Area: " + s2.area());
    }
}