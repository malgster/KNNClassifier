package Normalizer;

import Normalizers.EnumNormalizer;
import iris.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import titanic.Embarked;
import titanic.Gender;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests the enum normalizers
 */
public class EnumNormalizerTest {

    EnumNormalizer genderNorm;
    EnumNormalizer varietyNorm;

    EnumNormalizer embarkedNorm;

    /**
     * 3 normalizers for the 3 different enums existing
     */
    @BeforeEach
    void setUp() {
        genderNorm = new EnumNormalizer(Gender.class);
        varietyNorm = new EnumNormalizer(IrisVariety.class);
        embarkedNorm = new EnumNormalizer(Embarked.class);
    }


    /**
     * tests if the method recognizes how many elements there are in each enum
     */
    @Test
    void enum_normalizer_get_number_of_element_test() {
        assertEquals(3, genderNorm.getNumberOfElements());
        assertEquals(3, varietyNorm.getNumberOfElements());
        assertEquals(4, embarkedNorm.getNumberOfElements());
        // toujours plus loin dans les tests..
        enum Test {
            MALAK, LEA, CHARLES, GWEN
        }
        EnumNormalizer testNorm = new EnumNormalizer(Test.class);

        assertEquals(4, testNorm.getNumberOfElements());


    }

    /**
     * tests if normalize works
     */
    @Test
    void enum_Normalizer_Normalize_test() {
        assertEquals(1.0, genderNorm.normalize(Gender.MALE));
        assertEquals(0.0, genderNorm.normalize(Gender.FEMALE));
        assertEquals(0.5, genderNorm.normalize(Gender.OTHER));
        assertEquals(0.0, varietyNorm.normalize(IrisVariety.SETOSA));
        assertEquals(1.0, varietyNorm.normalize(IrisVariety.VIRGINICA));
        assertEquals(0.5, varietyNorm.normalize(IrisVariety.VERSICOLOR));
        assertEquals(0.0, embarkedNorm.normalize(Embarked.NULL));
        assertEquals(0.3, embarkedNorm.normalize(Embarked.C));
        assertEquals(0.6, embarkedNorm.normalize(Embarked.Q));
        assertEquals(1.0, embarkedNorm.normalize(Embarked.S));
    }

    /**
     * tests if denormalize
     */
    @Test
    void enum_Normalizer_Denormalize_test() {
        assertEquals(Gender.MALE, genderNorm.denormalize(1.0));
        assertEquals(Gender.OTHER, genderNorm.denormalize(0.5));
        assertEquals(Gender.FEMALE, genderNorm.denormalize(0.0));
        assertEquals(IrisVariety.SETOSA, varietyNorm.denormalize(0.0));
        assertEquals(IrisVariety.VERSICOLOR, varietyNorm.denormalize(0.5));
        assertEquals(IrisVariety.VIRGINICA, varietyNorm.denormalize(1.0));
        assertEquals(Embarked.NULL, embarkedNorm.denormalize(0.0));
        assertEquals(Embarked.C, embarkedNorm.denormalize(0.34));
        assertEquals(Embarked.Q, embarkedNorm.denormalize(0.67));
        assertEquals(Embarked.S, embarkedNorm.denormalize(1.0));

    }

    @Test
    void enum_string_value_test(){
        assertEquals("enum", genderNorm.stringValue());
    }
}
