package both;

import Distances.EuclidianDistance;
import Normalizers.EnumNormalizer;
import Normalizers.NullNormalizer;
import Normalizers.NumberNormalizer;
import dataInterfaces.IPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import titanic.Embarked;
import titanic.Gender;
import titanic.TitanicRawData;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * tests the classifier methods for the whole titanic.csv file
 */
class ClassifierTestTitanic {


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
    Column color;
    Classifier c;

    /**
     * set up the data
     */
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
        color = new Column("color", testDataSet);

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
        color.setNormalizer(new NullNormalizer());

        Column[] colonnes = new Column[]{passengerId, survived, passengerClass, name, gen, age, sibSp, parch, ticket, cabin, fare, embarked, color};
        testDataSet.setColumns(colonnes);
        testDataSet.setColumnClass(survived);
        testDataSet.setAllColors();
        c = new Classifier(testDataSet, testDataSet);
    }

    @Test
    void test_robustesse() {
        List<IPoint> pointsATester = new ArrayList<IPoint>();
        IPoint point;
        for (int i = 1; i < 100; i = i + 1) {
            point = testDataSet.getPoint(i);
            pointsATester.add(point);
            GenClass colorAvantClassify = point.getPointGenClass();
            c.classify(testDataSet.getPoint(i), c.closeNeighbours(point, 5, new EuclidianDistance()));
            GenClass colorApresClassify = point.getPointGenClass();
        }
        //assertTrue(c.robustness(5, new EuclidianDistance(), pointsATester)==100);
        assertTrue(c.robustness(5, new EuclidianDistance()) > 80);

    }


    @Test
    void test_csv_blank_space() {

    }

}

