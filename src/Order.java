
package src;

import java.util.ArrayList;
import java.util.List;

import src.ShippingCaluclator;
import src.ShippingCaluclator.Zone;

public class Order {

    public static final class Line {
        private final Product product;
        private final int quantity;

        public Line(Product product, int quantity) {
            if (product == null) throw new IllegalArgumentException("product required");
            if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
            this.product = product;
            this.quantity = quantity;
        }
        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }
        public double lineWeightKg() { return product.getWeightKg() * quantity; }
        public double merchandiseTotal() { return product.getUnitPrice() * quantity; }
        @Override public String toString() {
            return quantity + " × " + product.toString();
        }
    }

    private final List<Line> lines = new ArrayList<>();

    public void add(Product p, int qty) { lines.add(new Line(p, qty)); }

    public List<Line> lines() { return List.copyOf(lines); }

    public double totalMerchandise() {
        double sum = 0;
        for (Line l : lines) sum += l.merchandiseTotal();
        return Utils.roundMoney(sum);
    }

    public double totalWeightKg() {
        double w = 0;
        for (Line l : lines) w += l.lineWeightKg();
        return w;
    }

    public double shippingTotal(ShippingCaluclator.Zone zone) {
        double sum = 0;
        for (Line l : lines) {
            sum += ShippingCaluclator.shippingFor(l.lineWeightKg(), zone);
        }
        return Utils.roundMoney(sum);
    }

    public double grandTotal(ShippingCaluclator.Zone zone) {
        return Utils.roundMoney(totalMerchandise() + shippingTotal(zone));
    }
}
