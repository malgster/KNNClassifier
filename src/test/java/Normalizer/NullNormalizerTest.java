package Normalizer;

import Normalizers.NullNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests the NullNormalizers
 */
class NullNormalizerTest {

    NullNormalizer quelconque;

    @BeforeEach
    void setUp() {
        quelconque = new NullNormalizer();

    }

    @Test
    void null_normalize_test() {
        assertEquals(0.0, quelconque.normalize("Hello"));

    }

    @Test
    void null_denormalize_test() {
        assertEquals(17.0, quelconque.denormalize(17.0));
    }

    @Test
    void null_string_value_test(){
        assertEquals("null", quelconque.stringValue());
    }
}
