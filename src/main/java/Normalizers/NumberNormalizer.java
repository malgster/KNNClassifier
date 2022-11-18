package Normalizers;

import dataInterfaces.IValueNormalizer;
import utils.Format;


import java.math.RoundingMode;

public class NumberNormalizer implements IValueNormalizer {

    private final Double min;
    private final Double max;

    public NumberNormalizer(Double min, Double max){
        this.min = min;
        this.max = max;
    }

    @Override
    public double normalize(Object value) {
        double input = (((Double) value) - min) / (max-min);
        return Format.formatDouble(input, 2, RoundingMode.DOWN);
    }

    @Override
    public Object denormalize(Double value) {
         double input =  value*(max-min)+min;
        return Format.formatDouble(input, 1, RoundingMode.UP);
    }
}
