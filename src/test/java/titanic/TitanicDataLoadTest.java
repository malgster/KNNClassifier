package titanic;

import both.DataSet;
import iris.IrisRawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests if the csvLoad works for titanic
 */
public class TitanicDataLoadTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/titanic.csv";
    DataSet testDataSet = new DataSet("TitanicSet", TitanicRawData.class);

    @BeforeEach
    void setUp() {
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
    }

    @Test
    void test_good_loading_of_points() {
        assertNotNull(testDataSet.getMyPoints());
        assertNotNull(testDataSet.getPoint(15));
        assertNotNull(testDataSet.getPoint(53));
        assertNotNull(testDataSet.getPoint(87));
    }

    @Test
    void test_titanic_constructor_test(){
        TitanicRawData trd = new TitanicRawData(1,true,3,"test",Gender.FEMALE,23.0,2,3,"RR","ZR",34.0,Embarked.Q);
        assertEquals(1, trd.passengerId);
        assertTrue(trd.survived);
        assertEquals("test", trd.name);
    }

}
