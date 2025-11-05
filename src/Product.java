package src;

public class Product {
    private final String name;
    private final double weightKg;
    private final double unitPrice;

    public Product(String name, double weightKg, double unitPrice) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (weightKg < 0) throw new IllegalArgumentException("Weight cannot be negative");
        if (unitPrice < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.name = name.trim();
        this.weightKg = weightKg;
        this.unitPrice = unitPrice;
    }

    public String getName() { return name; }
    public double getWeightKg() { return weightKg; }
    public double getUnitPrice() { return unitPrice; }

    @Override
    public String toString() {
        return name + " (" + weightKg + "kg @ $" + String.format("%.2f", unitPrice) + ")";
    }
}