package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * rounds the numbers to scale numbers in the decimal
 */
public class Format {

    public static double formatDouble(double input, int scale, RoundingMode rm) {
        final BigDecimal bd = new BigDecimal(input).setScale(scale, rm);
        return bd.doubleValue();
    }
}
