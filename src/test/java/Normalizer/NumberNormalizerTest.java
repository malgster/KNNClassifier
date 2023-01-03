package Normalizer;

import Normalizers.NumberNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests the number normalizers
 */
public class NumberNormalizerTest {


    // random min and max values to avoid instantiating a dataset and column
    double min = 1.5;
    double max = 6.3;

    NumberNormalizer numNorm;

    @BeforeEach
    void setUp() {
        numNorm = new NumberNormalizer(min, max);
    }

    /**
     * test normalize method for the min and the max
     */
    @Test
    void number_normalizer_normalize_test() {
        assertEquals(0.75, numNorm.normalize(5.1));
        assertEquals(0.70, numNorm.normalize(4.9));
        assertEquals(0.41, numNorm.normalize(3.5));
        assertEquals(0.27, numNorm.normalize(2.8));
    }

    /**
     * test denormalize method for the min and the max
     */
    @Test
    void number_normalizer_denormalize_test() {
        assertEquals(5.1, numNorm.denormalize(0.75));
        assertEquals(4.9, numNorm.denormalize(0.70));
        assertEquals(3.5, numNorm.denormalize(0.41));
        assertEquals(2.8, numNorm.denormalize(0.27));
    }

    @Test
    void number_string_value_test(){
        assertEquals("number", numNorm.stringValue());
    }


}
