package both;

import Normalizers.EnumNormalizer;
import iris.IrisRawData;
import iris.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests the values of the attributes for iris
 */
public class ColumnIrisTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;
    Column nulle;

    /**
     * set up the dataset, columns, normalizers
     */
    @BeforeEach
    void setUp() {
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
        sepalLength = new Column("sepallength", testDataSet);
        sepalWidth = new Column("sepalwidth", testDataSet);
        petalLength = new Column("petallength", testDataSet);
        petalWidth = new Column("petalwidth", testDataSet);
        variety = new Column("variety", testDataSet);
        nulle = new Column("nulle", testDataSet);

        variety.setNormalizer(new EnumNormalizer(IrisVariety.class));

    }


    /**
     * tests the getValues
     */
    @Test
    void test_iris_data_load_good_attribute() {

        assertEquals(IrisVariety.SETOSA, testDataSet.getPoint(0).getValue(variety));
        assertEquals(IrisVariety.VERSICOLOR, testDataSet.getPoint(51).getValue(variety));
        assertEquals(IrisVariety.VIRGINICA, testDataSet.getPoint(102).getValue(variety));
        assertEquals(4.6, testDataSet.getPoint(3).getValue(sepalLength));
        assertEquals(4.9, testDataSet.getPoint(1).getValue(sepalLength));
        assertEquals(3.5, testDataSet.getPoint(0).getValue(sepalWidth));
        assertEquals(3.2, testDataSet.getPoint(2).getValue(sepalWidth));
        assertEquals(1.4, testDataSet.getPoint(0).getValue(petalLength));
        assertEquals(1.5, testDataSet.getPoint(3).getValue(petalLength));
        assertEquals(1.2, testDataSet.getPoint(7).getValue(petalWidth));
        assertEquals(1.2, testDataSet.getPoint(8).getValue(petalWidth));
        assertNull(testDataSet.getPoint(0).getValue(nulle));

    }

    @Test
    void is_normalizable_test(){
        assertFalse(nulle.isNormalizable());
        assertTrue(variety.isNormalizable());
    }

    @Test
    void get_dataset_test(){
        assertEquals(testDataSet, sepalLength.getDataset());
        assertEquals(testDataSet, variety.getDataset());
    }

    @Test
    void get_name_test(){
        assertEquals("sepallength", sepalLength.getName());
        assertEquals("petallength", petalLength.getName());
        assertEquals("petalwidth", petalWidth.getName());
    }




}
