package src;

public final class ShippingCaluclator {

    public enum Zone { LOCAL, REGIONAL, NATIONAL, INTERNATIONAL }

    // Simple tiered rates (you can tune these easily)
    private static final double HANDLING_FEE = 1.50; // USD per order-line
    private static final double FUEL_SURCHARGE_RATE = 0.06; // 6% of shipping cost (before tax)
    private static final double TAX_RATE = 0.07; // 7% tax on shipping

    private ShippingCaluclator() {}

    /** Returns shipping charge for a single line item (qty of same product). */
    public static double shippingFor(double totalWeightKg, Zone zone) {
        if (totalWeightKg <= 0) return 0.0;

        double basePerKg;
        switch (zone) {
            case LOCAL:         basePerKg = 1.00; break;
            case REGIONAL:      basePerKg = 1.50; break;
            case NATIONAL:      basePerKg = 2.10; break;
            case INTERNATIONAL: basePerKg = 4.50; break;
            default:            basePerKg = 2.00;
        }

        // tiered discount: heavier -> cheaper per-kg
        double effectivePerKg =
                (totalWeightKg < 2)   ? basePerKg * 1.00 :
                (totalWeightKg < 5)   ? basePerKg * 0.95 :
                (totalWeightKg < 10)  ? basePerKg * 0.90 :
                                        basePerKg * 0.85;

        double cost = HANDLING_FEE + (effectivePerKg * totalWeightKg);
        double fuel = cost * FUEL_SURCHARGE_RATE;
        double taxed = (cost + fuel) * (1 + TAX_RATE);

        return Utils.roundMoney(taxed);
    }
}