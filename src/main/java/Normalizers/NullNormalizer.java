package Normalizers;

import dataInterfaces.IValueNormalizer;

/**
 * null normalizer, does nothing
 */
public class NullNormalizer implements IValueNormalizer {

    @Override
    public double normalize(Object value) {
        return 0;
    }

    @Override
    public Object denormalize(Double value) {
        return value;
    }

    @Override
    public String stringValue() {
        return "null";
    }
}
