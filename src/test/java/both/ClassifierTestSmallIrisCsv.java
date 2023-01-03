package both;

import Distances.EuclidianDistance;
import Normalizers.NullNormalizer;
import Normalizers.NumberNormalizer;
import dataInterfaces.IPoint;
import iris.IrisRawData;
import iris.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests the classifier methods for a small csv
 */
class ClassifierTestSmallIrisCsv {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/test_iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;
    Column color;
    Classifier c;
    NumberNormalizer petalLengthNormalizer;

    /**
     * sets the dataset, its columns, normalizers and classifier
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
        petalLengthNormalizer = new NumberNormalizer(testDataSet.minValue(petalLength).doubleValue(), testDataSet.maxValue(petalLength).doubleValue());
        petalLength.setNormalizer(petalLengthNormalizer);
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
     * tests if the actual neighbours are the expected ones
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
     * tests if classify changes the color of the point
     */
    @Test
    void test_classify() {
        IPoint ird = testDataSet.getPoint(0);
        assertEquals(GenClass.NULL, ird.getPointGenClass());
        c.classify(ird, c.closeNeighbours(ird, 3, new EuclidianDistance()));
        assertNotEquals(GenClass.NULL, ird.getPointGenClass());
        ird. setRealClassFromGenClass();
        assertEquals(GenClass.SECONDCLASS, ird.getPointGenClass());

    }

    /**
     * tests if the column is in the good dataset
     */
    @Test
    void test_good_dataset() {
        assertEquals(testDataSet, variety.getDataset());
    }

    /**
     * tests if the column has the good normalizer
     */
    @Test
    void test_good_normalizer() {
        assertEquals(petalLengthNormalizer, petalLength.getNormalizer());
    }

    @Test
    void test_denormalize() {
        assertTrue((Double) petalWidth.getDenormalizedValue(testDataSet.getPoint(1).getNormalizedValue(petalWidth)) > 1);
    }

    @Test
    void test_set_real_class_from_gen_class(){
        IrisRawData ird = (IrisRawData) testDataSet.getPoint(0);
        ird.setPointGenClass(GenClass.FIRSTCLASS);
        ird.setRealClassFromGenClass();
        assertEquals(IrisVariety.SETOSA, ird.getValue(variety));
    }
}
