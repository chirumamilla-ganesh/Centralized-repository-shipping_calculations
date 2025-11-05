package src;
public class App {
    public static void main(String[] args) {
        Product phone   = new Product("Phone", 0.25, 299.99);
        Product laptop  = new Product("Laptop", 1.60, 899.00);
        Product dumbbell= new Product("Dumbbell", 5.00, 34.50);

        Order order = new Order();
        order.add(phone, 2);
        order.add(laptop, 1);
        order.add(dumbbell, 3);

        var zone = ShippingCaluclator.Zone.NATIONAL;

        System.out.println("Order Lines:");
        for (Order.Line l : order.lines()) {
            System.out.println("  - " + l);
        }
        System.out.println("\nMerchandise: $" + order.totalMerchandise());
        System.out.println("Shipping (" + zone + "): $" + order.shippingTotal(zone));
        System.out.println("Grand Total: $" + order.grandTotal(zone));
    }
}