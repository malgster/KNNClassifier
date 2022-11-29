package dataInterfaces;

public interface IValueNormalizer {


    double normalize(Object value);

    Object denormalize(Double value);

    String stringValue();
}
