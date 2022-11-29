package distances;

import Distances.ManhatthanDistance;
import Normalizers.EnumNormalizer;
import Normalizers.NullNormalizer;
import Normalizers.NumberNormalizer;
import both.Column;
import both.DataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import titanic.Embarked;
import titanic.Gender;
import titanic.TitanicRawData;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests the manhattan distance for titanic given there are enums whereas iris only has numberNormalizers
 */
class ManhattanDistanceTestTitanic {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/titanic.csv";
    DataSet testDataSet = new DataSet("TitanicSet", TitanicRawData.class);
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

        passengerId.setNormalizer(new NumberNormalizer(testDataSet.minValue(passengerId).doubleValue(), testDataSet.maxValue(passengerId).doubleValue()));
        passengerClass.setNormalizer(new NumberNormalizer(testDataSet.minValue(passengerClass).doubleValue(), testDataSet.maxValue(passengerClass).doubleValue()));
        age.setNormalizer(new NumberNormalizer(testDataSet.minValue(age).doubleValue(), testDataSet.maxValue(age).doubleValue()));
        sibSp.setNormalizer(new NumberNormalizer(testDataSet.minValue(sibSp).doubleValue(), testDataSet.maxValue(sibSp).doubleValue()));
        parch.setNormalizer(new NumberNormalizer(testDataSet.minValue(parch).doubleValue(), testDataSet.maxValue(parch).doubleValue()));
        fare.setNormalizer(new NumberNormalizer(testDataSet.minValue(fare).doubleValue(), testDataSet.maxValue(fare).doubleValue()));
        gen.setNormalizer(new EnumNormalizer(Gender.class));
        embarked.setNormalizer(new EnumNormalizer(Embarked.class));
        survived.setNormalizer(new NullNormalizer());
        ticket.setNormalizer(new NullNormalizer());
        name.setNormalizer(new NullNormalizer());

        Column[] colonnes = new Column[]{passengerId, survived, passengerClass, name, gen, age, sibSp, parch, ticket, cabin, fare, embarked};
        testDataSet.setColumns(colonnes);
        testDataSet.setColumnClass(survived);
        testDataSet.setAllColors();
    }

    /**
     * tests calculateDistance
     */
    @Test
    void test_distance_manhattan_titanic() {
        assertEquals(1.39, new ManhatthanDistance().calculateDistance(testDataSet.getPoint(1), testDataSet.getPoint(2), testDataSet, testDataSet));
        assertEquals(2.709, new ManhatthanDistance().calculateDistance(testDataSet.getPoint(1), testDataSet.getPoint(5), testDataSet, testDataSet));
    }

}
