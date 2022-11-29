package iris;


import both.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * tests if the csv is loaded
 */
public class IrisDataLoadTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);

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


}
