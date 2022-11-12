package both;

import iris.IrisRawData;
import iris.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColumnIrisTest {

    String fileName= Paths.get(".").normalize().toAbsolutePath()+ File.separator+"ressources/iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);
    Column sepalLength;
    Column sepalWidth;
    Column petalLength;
    Column petalWidth;
    Column variety;

    @BeforeEach
    void setUp(){
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
        sepalLength = new Column("sepallength" , testDataSet);
        sepalWidth = new Column("sepalwidth" , testDataSet);
        petalLength = new Column("petallength" , testDataSet);
        petalWidth = new Column("petalwidth" , testDataSet);
        variety = new Column("variety", testDataSet);

    }



    @Test
    void test_iris_data_load_good_attribute(){

        assertEquals(IrisVariety.SETOSA, testDataSet.getMyPoints().get(0).getValue(variety));
        assertEquals(IrisVariety.VERSICOLOR, testDataSet.getMyPoints().get(51).getValue(variety));
        assertEquals(IrisVariety.VIRGINICA, testDataSet.getMyPoints().get(102).getValue(variety));
        assertEquals(4.6, testDataSet.getMyPoints().get(3).getValue(sepalLength));
        assertEquals(4.9, testDataSet.getMyPoints().get(1).getValue(sepalLength));
        assertEquals(3.5, testDataSet.getMyPoints().get(0).getValue(sepalWidth));
        assertEquals(3.2, testDataSet.getMyPoints().get(2).getValue(sepalWidth));
        assertEquals(1.4, testDataSet.getMyPoints().get(0).getValue(petalLength));
        assertEquals(1.5, testDataSet.getMyPoints().get(3).getValue(petalLength));
        assertEquals(1.2, testDataSet.getMyPoints().get(7).getValue(petalWidth));
        assertEquals(1.2, testDataSet.getMyPoints().get(8).getValue(petalWidth));

    }

}
