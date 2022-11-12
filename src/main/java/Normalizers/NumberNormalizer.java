package Normalizers;

import dataInterfaces.IValueNormalizer;

public class NumberNormalizer implements IValueNormalizer {

    private final Double min;
    private final Double max;

    public NumberNormalizer(Double min, Double max){
        this.min = min;
        this.max = max;
    }

    @Override
    public double normalize(Object value) {
        return ((Double) value-min)/(max-min);
    }

    @Override
    public Object denormalize(Double value) {
        return value*(max-min)+min;
    }
}
