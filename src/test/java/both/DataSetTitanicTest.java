package both;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import titanic.TitanicRawData;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests the dataset methods for titanic
 */
class DataSetTitanicTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/titanic.csv";
    DataSet testDataSet = new DataSet("IrisSet", TitanicRawData.class);
    Column passengerId;
    Column survived;
    Column passengerClass;
    Column name;
    Column gen;
    Column age;
    Column sibSp;
    Column parch;
    Column ticket;
    Column cabin;
    Column fare;
    Column embarked;

    @BeforeEach
    void setUp() {
        testDataSet.setMyPoints(CsvLoader.load(fileName, TitanicRawData.class));
        passengerId = new Column("passengerid", testDataSet);
        survived = new Column("survived", testDataSet);
        passengerClass = new Column("passengerclass", testDataSet);
        name = new Column("name", testDataSet);
        gen = new Column("gen", testDataSet);
        age = new Column("age", testDataSet);
        sibSp = new Column("sibSp", testDataSet);
        parch = new Column("parch", testDataSet);
        ticket = new Column("ticket", testDataSet);
        cabin = new Column("cabin", testDataSet);
        fare = new Column("fare", testDataSet);
        embarked = new Column("embarked", testDataSet);
    }

    /**
     * tests min and max for age
     */
    @Test
    void test_min_max_value() {
        assertEquals(0.0, testDataSet.minValue(age));
        assertEquals(80.0, testDataSet.maxValue(age));
    }

}
