package Normalizers;

import dataInterfaces.IValueNormalizer;

/**
 * normalizer set for boolean columns
 */
public class BooleanNormalizer implements IValueNormalizer {
    @Override
    public double normalize(Object value) {
        return (boolean) value ? 1 : 0;
    }

    @Override
    public Object denormalize(Double value) {
        return (value == 1);
    }

    @Override
    public String stringValue() {
        return "boolean";
    }
}
