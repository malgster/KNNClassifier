package both;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iris.IrisRawData;

class DataSetIrisTest {

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
	void test_min_max_value() {
		assertEquals(4.3, sepalLength.getMin());
		assertEquals(7.9, sepalLength.getMax());
	}

}
