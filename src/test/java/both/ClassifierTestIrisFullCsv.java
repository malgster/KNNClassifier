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
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * tests the classifier methods for the full iris.csv
 */
class ClassifierTestIrisFullCsv {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;
    Column color;
    Classifier c;

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
     * verifies if the robustness if good enough
     */
    @Test
    void test_robustesse() {
        List<IPoint> pointsATester = new ArrayList<IPoint>();
        IPoint point;
        for (int i = 1; i < 100; i = i + 8) {
            point = testDataSet.getPoint(i);
            pointsATester.add(point);

        }
        // si jamais, c'est normal d'avoir 100 de robustesse ici car on a prit que 10 du dataset
<<<<<<< HEAD
        assertEquals(100, c.robustness(5, new EuclidianDistance(), pointsATester));
        assertTrue(c.robustness(5, new EuclidianDistance()) > 90);
        System.out.println(c.robustness(5, new EuclidianDistance()));

        double robustesse = c.robustness(5, new EuclidianDistance());
        System.out.println(robustesse);
        assertTrue(robustesse != 100 && robustesse>90 );

=======
        double robustesse = c.robustness(5, new EuclidianDistance());
        System.out.println(robustesse);
        assertTrue(robustesse != 100 && robustesse>90 );
>>>>>>> 86b1c94e2db9285aded7703350f5908d094327ed
    }


}
