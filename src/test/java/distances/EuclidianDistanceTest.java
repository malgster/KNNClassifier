package distances;

import Distances.EuclidianDistance;
import Normalizers.EnumNormalizer;
import Normalizers.NumberNormalizer;
import both.Classifier;
import both.Column;
import both.CsvLoader;
import both.DataSet;
import iris.IrisRawData;
import iris.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EuclidianDistanceTest {

    String fileName= Paths.get(".").normalize().toAbsolutePath()+ File.separator+"ressources/iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;
    Classifier c = new Classifier(testDataSet);

    @BeforeEach
    void setUp(){
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
        sepalLength = new Column("sepallength" , testDataSet);
        sepalWidth = new Column("sepalwidth" , testDataSet);
        petalLength = new Column("petallength" , testDataSet);
        petalWidth = new Column("petalwidth" , testDataSet);
        variety = new Column("variety", testDataSet);

        //on set les normalizers, sinon nullPointer au getNormalizedValue
        Column[] colonnes = new Column[] {sepalLength, sepalWidth, petalLength, petalWidth, variety};
        petalLength.setNormalizer(new NumberNormalizer(testDataSet.minValue(petalLength).doubleValue(), testDataSet.maxValue(petalLength).doubleValue()));
        petalWidth.setNormalizer(new NumberNormalizer(testDataSet.minValue(petalWidth).doubleValue(), testDataSet.maxValue(petalWidth).doubleValue()));
        sepalLength.setNormalizer(new NumberNormalizer(testDataSet.minValue(sepalLength).doubleValue(), testDataSet.maxValue(sepalLength).doubleValue()));
        sepalWidth.setNormalizer(new NumberNormalizer(testDataSet.minValue(sepalWidth).doubleValue(), testDataSet.maxValue(sepalWidth).doubleValue()));
        variety.setNormalizer(new EnumNormalizer(IrisVariety.class));

        //on donne les colonnes au dataSet (sinon nullPointer à distance)
        testDataSet.setColumns(colonnes);

    }

    @Test
    void euclidian_distance_Test() {
        assertEquals(0.218, new EuclidianDistance().calculateDistance(testDataSet.getMyPoints().get(0), testDataSet.getMyPoints().get(1), testDataSet));
        assertEquals(0.221, new EuclidianDistance().calculateDistance(testDataSet.getMyPoints().get(0), testDataSet.getMyPoints().get(3), testDataSet));
        assertEquals(0.251, new EuclidianDistance().calculateDistance(testDataSet.getMyPoints().get(1), testDataSet.getMyPoints().get(4), testDataSet));
        assertEquals(0.375, new EuclidianDistance().calculateDistance(testDataSet.getMyPoints().get(2), testDataSet.getMyPoints().get(5), testDataSet));
    }


}
