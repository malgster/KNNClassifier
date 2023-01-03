package both;

import Distances.EuclidianDistance;
import Normalizers.NullNormalizer;
import Normalizers.NumberNormalizer;
import dataInterfaces.IPoint;
import iris.IrisRawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * tests the classifier methods
 */
class ClassifierTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/test_iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;
    Column color;
    Classifier c;

    /**
     * sets the dataset with its columns, its normalizer and its classifier
     */
    @BeforeEach
    void setUp() {
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
        sepalLength = new Column("sepallength", testDataSet);
        sepalWidth = new Column("sepalwidth", testDataSet);
        petalLength = new Column("petallength", testDataSet);
        petalWidth = new Column("petalwidth", testDataSet);
        variety = new Column("variety", testDataSet);
        color = new Column("color", testDataSet);

        //on set les normalizers, sinon nullPointer au getNormalizedValue
        petalLength.setNormalizer(new NumberNormalizer(testDataSet.minValue(petalLength).doubleValue(), testDataSet.maxValue(petalLength).doubleValue()));
        petalWidth.setNormalizer(new NumberNormalizer(testDataSet.minValue(petalWidth).doubleValue(), testDataSet.maxValue(petalWidth).doubleValue()));
        sepalLength.setNormalizer(new NumberNormalizer(testDataSet.minValue(sepalLength).doubleValue(), testDataSet.maxValue(sepalLength).doubleValue()));
        sepalWidth.setNormalizer(new NumberNormalizer(testDataSet.minValue(sepalWidth).doubleValue(), testDataSet.maxValue(sepalWidth).doubleValue()));
        variety.setNormalizer(new NullNormalizer());
        color.setNormalizer(new NullNormalizer());
        Column[] colonnes = new Column[]{sepalLength, sepalWidth, petalLength, petalWidth, variety, color};


        //on donne les colonnes au dataSet (sinon nullPointer Ã  distance)
        testDataSet.setColumns(colonnes);
        testDataSet.setColumnClass(variety);
        testDataSet.setAllColors();
        c = new Classifier(testDataSet, testDataSet);
    }

    /**
     * tests if the expected closeNeighbours are found
     */
    @Test
    void test_close_neighbours() {
        List<IPoint> suspectedNeighbours = new ArrayList<IPoint>();

        suspectedNeighbours.add(testDataSet.getPoint(1));
        suspectedNeighbours.add(testDataSet.getPoint(2));
        suspectedNeighbours.add(testDataSet.getPoint(3));

        List<? extends IPoint> neighbours = c.closeNeighbours(testDataSet.getPoint(0), 3, new EuclidianDistance());

        assertEquals(3, neighbours.size());
        assertEquals(suspectedNeighbours, neighbours);
        assertEquals(testDataSet.getPoint(1), suspectedNeighbours.get(0));
        assertEquals(testDataSet.getPoint(1).getPointGenClass(), suspectedNeighbours.get(0).getPointGenClass());
    }


    /**
     * tests if the classify method changes a point's color
     */
    @Test
    void test_classify() {
        IPoint ird = testDataSet.getPoint(0);
        c.classify(ird, c.closeNeighbours(ird, 3, new EuclidianDistance()));
        assertNotEquals(GenClass.NULL, ird.getPointGenClass());
        assertEquals(GenClass.SECONDCLASS, ird.getPointGenClass());
    }



}
