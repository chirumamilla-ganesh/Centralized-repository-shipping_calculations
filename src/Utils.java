package src;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Utils {
    private Utils() {}
    public static double roundMoney(double v) {
        return new BigDecimal(v).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}