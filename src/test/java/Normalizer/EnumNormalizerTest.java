package Normalizer;

import Normalizers.EnumNormalizer;
import iris.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import titanic.Embarked;
import titanic.Gender;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumNormalizerTest {

    EnumNormalizer genderNorm;
    EnumNormalizer varietyNorm;

    EnumNormalizer embarkedNorm;

    @BeforeEach
    void setUp(){
        genderNorm = new EnumNormalizer(Gender.class);
        varietyNorm = new EnumNormalizer(IrisVariety.class);
        embarkedNorm = new EnumNormalizer(Embarked.class);
    }

    @Test
    void enum_normalizer_get_number_of_element_test(){
        assertEquals(2,genderNorm.getNumberOfElements());
        assertEquals(3,varietyNorm.getNumberOfElements());
        assertEquals(3,embarkedNorm.getNumberOfElements());
        // toujours plus loin dans les tests..
        enum Test {
            MALAK, LEA, CHARLES, GWEN
        }
        EnumNormalizer testNorm = new EnumNormalizer(Test.class);

        assertEquals(4, testNorm.getNumberOfElements());





    }

    @Test
        void enum_Normalizer_Normalize_test(){
        assertEquals(1.0, genderNorm.normalize(Gender.MALE));
        assertEquals(0.0, genderNorm.normalize(Gender.FEMALE));
        assertEquals(0.0, varietyNorm.normalize(IrisVariety.SETOSA));
        assertEquals(1.0, varietyNorm.normalize(IrisVariety.VIRGINICA));
        assertEquals(0.5, varietyNorm.normalize(IrisVariety.VERSICOLOR));
        assertEquals(0.0, embarkedNorm.normalize(Embarked.C));
        assertEquals(0.5, embarkedNorm.normalize(Embarked.Q));
        assertEquals(1.0, embarkedNorm.normalize(Embarked.S));
    }

    @Test
        void enum_Normalizer_Denormalize_test(){
        assertEquals(Gender.MALE, genderNorm.denormalize(1.0));
        assertEquals(Gender.FEMALE, genderNorm.denormalize(0.0));
        assertEquals(IrisVariety.SETOSA, varietyNorm.denormalize(0.0));
        assertEquals(IrisVariety.VERSICOLOR, varietyNorm.denormalize(0.5));
        assertEquals(IrisVariety.VIRGINICA, varietyNorm.denormalize(1.0));
        assertEquals(Embarked.C, embarkedNorm.denormalize(0.0));
        assertEquals(Embarked.Q, embarkedNorm.denormalize(0.5));
        assertEquals(Embarked.S, embarkedNorm.denormalize(1.0));

    }
}
