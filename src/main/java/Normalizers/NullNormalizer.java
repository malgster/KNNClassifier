package Normalizers;

import dataInterfaces.IValueNormalizer;

public class NullNormalizer implements IValueNormalizer {

    @Override
    public double normalize(Object value) {
        return 0;
    }

    @Override
    public Object denormalize(Double value) {
        return value;
    }
}
