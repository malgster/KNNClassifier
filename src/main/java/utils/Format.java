package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Format {

    public static double formatDouble(double input, int scale, RoundingMode rm){
        final BigDecimal bd = new BigDecimal(input).setScale(scale, rm);
        return bd.doubleValue();
    }
}
