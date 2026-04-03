import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Order {
    String product;
    int quantity;
    double price;

    public Order(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalCost() {
        return quantity * price;
    }
}

public class OrderStreamExample {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order("Laptop", 2, 50000.0),
                new Order("Mobile", 3, 20000.0),
                new Order("Tablet", 1, 15000.0)
        );

        // Calculate total cost of all orders
        double totalCost = orders.stream()
                .mapToDouble(Order::getTotalCost)
                .sum();
        System.out.println("Total cost: " + totalCost);

        // Find orders with quantity > 2
        List<Order> bulkOrders = orders.stream()
                .filter(o -> o.getQuantity() > 2)
                .collect(Collectors.toList());
        System.out.println("Bulk orders: " + bulkOrders.stream().map(Order::getProduct).collect(Collectors.toList()));

        // Find the most expensive order
        Order mostExpensiveOrder = orders.stream()
                .max((o1, o2) -> Double.compare(o1.getTotalCost(), o2.getTotalCost()))
                .orElse(null);
        System.out.println("Most expensive order: " + mostExpensiveOrder.getProduct());
    }
}