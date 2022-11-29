package distances;

import Distances.ManhatthanDistance;
import Normalizers.NullNormalizer;
import Normalizers.NumberNormalizer;
import both.Column;
import both.DataSet;
import iris.IrisRawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests the methods of ManhattanDistance
 */
public class ManhatthanDistanceTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;

    @BeforeEach
    void setUp() {
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
        sepalLength = new Column("sepallength", testDataSet);
        sepalWidth = new Column("sepalwidth", testDataSet);
        petalLength = new Column("petallength", testDataSet);
        petalWidth = new Column("petalwidth", testDataSet);
        variety = new Column("variety", testDataSet);

        //on set les normalizers, sinon nullPointer au getNormalizedValue
        Column[] colonnes = new Column[]{sepalLength, sepalWidth, petalLength, petalWidth, variety};
        petalLength.setNormalizer(new NumberNormalizer(testDataSet.minValue(petalLength).doubleValue(), testDataSet.maxValue(petalLength).doubleValue()));
        petalWidth.setNormalizer(new NumberNormalizer(testDataSet.minValue(petalWidth).doubleValue(), testDataSet.maxValue(petalWidth).doubleValue()));
        sepalLength.setNormalizer(new NumberNormalizer(testDataSet.minValue(sepalLength).doubleValue(), testDataSet.maxValue(sepalLength).doubleValue()));
        sepalWidth.setNormalizer(new NumberNormalizer(testDataSet.minValue(sepalWidth).doubleValue(), testDataSet.maxValue(sepalWidth).doubleValue()));
        variety.setNormalizer(new NullNormalizer());

        //on donne les colonnes au dataSet (sinon nullPointer à distance)
        testDataSet.setColumns(colonnes);
        testDataSet.setColumnClass(variety);

    }

    /**
     * tests calculate distance
     */
    @Test
    void manhatthan_distance_test() {
        assertEquals(0.150, new ManhatthanDistance().calculateDistance(testDataSet.getPoint(1), testDataSet.getPoint(2), testDataSet, testDataSet));
        assertEquals(0.7, new ManhatthanDistance().calculateDistance(testDataSet.getPoint(1), testDataSet.getPoint(5), testDataSet, testDataSet));
        assertEquals(0.34, new ManhatthanDistance().calculateDistance(testDataSet.getPoint(3), testDataSet.getPoint(4), testDataSet, testDataSet));
        assertEquals(0.239, new ManhatthanDistance().calculateDistance(testDataSet.getPoint(2), testDataSet.getPoint(0), testDataSet, testDataSet));
    }

}
