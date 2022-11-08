package dataInterfaces;

public interface IValueNormalizer {

    public enum TypeNormalizer {
        NUMBER_NORMALIZER,  BOOLEAN_NORMALIZER, OTHER_NORMALIZER;
    }

    public double normalize(Object value);
    public Object denormalize(Double value);


}
