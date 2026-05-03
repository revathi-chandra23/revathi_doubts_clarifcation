import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name; double price; String category;
    Product(String name, double price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
    public String toString() { return name + ":" + price; }
}

public class StreamDemo {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 80000, "Electronics"),
            new Product("Mouse", 500, "Electronics"),
            new Product("Shirt", 1200, "Clothing"),
            new Product("Keyboard", 1500, "Electronics"),
            new Product("Jeans", 2000, "Clothing")
        );
        
        // 1. Filter: Electronics with price > 1000
        List<Product> filtered = products.stream()
                .filter(p -> p.category.equals("Electronics"))
                .filter(p -> p.price > 1000)
                .collect(Collectors.toList());
        System.out.println("Electronics >1000: " + filtered);
        
        // 2. Map: Get all product names
        List<String> names = products.stream()
                .map(p -> p.name)
                .collect(Collectors.toList());
        System.out.println("Names: " + names);
        
        // 3. Sum: Total price using mapToDouble
        double total = products.stream()
                .mapToDouble(p -> p.price)
                .sum();
        System.out.println("Total Price: " + total);
        
        // 4. Grouping by category
        Map<String, List<Product>> byCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));
        System.out.println("Grouped: " + byCategory);
    }
}