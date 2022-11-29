package both;

import dataInterfaces.IPoint;
import iris.IrisRawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * tests the dataset values for iris
 */
class DataSetIrisTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/iris.csv";
    String fileName2 = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/test_iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    DataSet testDataSet2 = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;

    @BeforeEach
    void setUp() {
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
        testDataSet2.setMyPoints(CsvLoader.load(fileName2, IrisRawData.class));
        sepalLength = new Column("sepallength", testDataSet);
        sepalWidth = new Column("sepalwidth", testDataSet);
        petalLength = new Column("petallength", testDataSet);
        petalWidth = new Column("petalwidth", testDataSet);
        variety = new Column("variety", testDataSet);
    }

    /**
     * tests min and max values of the attributes
     */
    @Test
    void test_min_max_value() {
        assertEquals(4.3, testDataSet.minValue(sepalLength));
        assertEquals(7.9, testDataSet.maxValue(sepalLength));
    }

    @Test
    void test_getTitle() {
        assertEquals("IrisSet", testDataSet.getTitle());
    }

    /**
     * tests the addLines method from dataSet
     */
    @Test
    void test_add_lines() {
        int nbLines = testDataSet.getNbLines();
        testDataSet.addAllLine((List<IPoint>) testDataSet2.getMyPoints());
        assertNotEquals(nbLines, testDataSet.getNbLines());
    }

}
