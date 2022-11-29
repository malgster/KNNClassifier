package Normalizer;

import Normalizers.BooleanNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests the boolean normalizers
 */
public class BooleanNormalizerTest {

    BooleanNormalizer booNorm;

    @BeforeEach
    void setUp() {
        booNorm = new BooleanNormalizer();
    }

    /**
     * tests if the normalized values are what is expected
     */
    @Test
    void boolean_normalizer_normalize_test() {
        assertEquals(1, booNorm.normalize(true));
        assertEquals(0, booNorm.normalize(false));
    }

    /**
     * tests if the denormalized values are ok
     */
    @Test
    void boolean_normalizer_denormalize_test() {
        assertEquals(true, booNorm.denormalize(1.0));
        assertEquals(false, booNorm.denormalize(0.0));
    }

}
