package both;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Normalizers.EnumNormalizer;
import Normalizers.NumberNormalizer;
import dataInterfaces.IColumn;
import iris.IrisRawData;
import iris.IrisVariety;

class ClassifierTest {
	
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

	/*@Test
	void test_distance_non_normalisee_fonctionnelle() {
		assertEquals(0.538,(c.distanceEuclidienne(testDataSet.getMyPoints().get(0), testDataSet.getMyPoints().get(1), testDataSet)));
		assertEquals(0.699, (c.distanceManhatthan(testDataSet.getMyPoints().get(0), testDataSet.getMyPoints().get(1), testDataSet)));
	}

	/*@Test
	void test_distance_normalisee_fonctionnelle() {
		assertEquals(0.150, (c.distanceManhatthanNormalisee(testDataSet.getMyPoints().get(1), testDataSet.getMyPoints().get(2), testDataSet)));
		assertEquals(0.218, (c.distanceEuclidienneNormalisee(testDataSet.getMyPoints().get(0), testDataSet.getMyPoints().get(1), testDataSet)));
	}*/
	


}
