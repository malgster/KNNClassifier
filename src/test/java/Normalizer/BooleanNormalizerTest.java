package Normalizer;

import Normalizers.BooleanNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanNormalizerTest {

    BooleanNormalizer booNorm;

    @BeforeEach
    void setUp(){
        booNorm = new BooleanNormalizer();
    }

    @Test
    void boolean_normalizer_normalize_test(){
        assertEquals(1, booNorm.normalize(true));
        assertEquals(0, booNorm.normalize(false));
    }

    @Test
    void boolean_normalizer_denormalize_test(){
        assertEquals(true, booNorm.denormalize(1.0));
        assertEquals(false, booNorm.denormalize(0.0));
    }

}
