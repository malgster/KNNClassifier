package iris;



import both.CsvLoader;
import both.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class IrisDataLoadTest {

    String fileName= Paths.get(".").normalize().toAbsolutePath()+ File.separator+"ressources/iris.csv";
    DataSet testDataSet = new DataSet("IrisSet", IrisRawData.class);

    @BeforeEach
    void setUp(){
        testDataSet.setMyPoints(CsvLoader.load(fileName, IrisRawData.class));
    }

    @Test
    void test_good_loading_of_points(){
        assertNotNull(testDataSet.getMyPoints());
        assertNotNull(testDataSet.getMyPoints().get(15));
        assertNotNull(testDataSet.getMyPoints().get(53));
        assertNotNull(testDataSet.getMyPoints().get(87));
    }






}
