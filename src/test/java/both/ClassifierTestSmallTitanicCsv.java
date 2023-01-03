package both;

import Distances.EuclidianDistance;
import Normalizers.EnumNormalizer;
import Normalizers.NullNormalizer;
import Normalizers.NumberNormalizer;
import dataInterfaces.IPoint;
import iris.IrisRawData;
import iris.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import titanic.Embarked;
import titanic.Gender;
import titanic.TitanicRawData;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * tests the classifier methods for a small titanic csv
 */
class ClassifierTestSmallTitanicCsv {


    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/titanic_test.csv";
    DataSet testDataSet = new DataSet("TitanicSet", TitanicRawData.class);

    String fileName2 = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/titanic.csv";
    DataSet testDataSetToHelp = new DataSet("TitanicSet", TitanicRawData.class);
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

    Column passengerId2;
    Column survived2;
    Column passengerClass2;
    Column name2;
    Column gen2;
    Column age2;
    Column sibSp2;
    Column parch2;
    Column ticket2;
    Column cabin2;
    Column fare2;
    Column embarked2;
    Column color2;
    Classifier c;

    /**
     * set up the dataset, columns, normalizers and classifier
     */
    @BeforeEach
    void setUp() {
        testDataSet.setMyPoints(CsvLoader.load(fileName, TitanicRawData.class));
        testDataSetToHelp.setMyPoints(CsvLoader.load(fileName2, TitanicRawData.class));
        passengerId = new Column("passengerid", testDataSet);
        passengerId2 = new Column("passengerid", testDataSetToHelp);
        survived = new Column("survived", testDataSet);
        survived2 = new Column("survived", testDataSetToHelp);
        passengerClass = new Column("passengerclass", testDataSet);
        passengerClass2 = new Column("passengerclass", testDataSetToHelp);
        name = new Column("name", testDataSet);
        name2 = new Column("name", testDataSetToHelp);
        gen = new Column("gen", testDataSet);
        gen2 = new Column("gen", testDataSetToHelp);
        age = new Column("age", testDataSet);
        age2 = new Column("age", testDataSetToHelp);
        sibSp = new Column("sibSp", testDataSet);
        sibSp2 = new Column("sibSp", testDataSetToHelp);
        parch = new Column("parch", testDataSet);
        parch2 = new Column("parch", testDataSetToHelp);
        ticket = new Column("ticket", testDataSet);
        ticket2 = new Column("ticket", testDataSetToHelp);
        cabin = new Column("cabin", testDataSet);
        cabin2 = new Column("cabin", testDataSetToHelp);
        fare = new Column("fare", testDataSet);
        fare2 = new Column("fare", testDataSetToHelp);
        embarked = new Column("embarked", testDataSet);
        embarked2 = new Column("embarked", testDataSetToHelp);
        color = new Column("color", testDataSet);
        color2 = new Column("color", testDataSetToHelp);

        passengerId.setNormalizer(new NumberNormalizer(testDataSet.minValue(passengerId).doubleValue(), testDataSet.maxValue(passengerId).doubleValue()));
        passengerId2.setNormalizer(new NumberNormalizer(testDataSetToHelp.minValue(passengerId2).doubleValue(), testDataSetToHelp.maxValue(passengerId2).doubleValue()));
        passengerClass.setNormalizer(new NumberNormalizer(testDataSet.minValue(passengerClass).doubleValue(), testDataSet.maxValue(passengerClass).doubleValue()));
        passengerClass2.setNormalizer(new NumberNormalizer(testDataSetToHelp.minValue(passengerClass2).doubleValue(), testDataSetToHelp.maxValue(passengerClass2).doubleValue()));
        age.setNormalizer(new NumberNormalizer(testDataSet.minValue(age).doubleValue(), testDataSet.maxValue(age).doubleValue()));
        age2.setNormalizer(new NumberNormalizer(testDataSetToHelp.minValue(age2).doubleValue(), testDataSet.maxValue(age2).doubleValue()));
        sibSp.setNormalizer(new NumberNormalizer(testDataSet.minValue(sibSp).doubleValue(), testDataSet.maxValue(sibSp).doubleValue()));
        sibSp2.setNormalizer(new NumberNormalizer(testDataSetToHelp.minValue(sibSp2).doubleValue(), testDataSet.maxValue(sibSp2).doubleValue()));
        parch.setNormalizer(new NumberNormalizer(testDataSet.minValue(parch).doubleValue(), testDataSet.maxValue(parch).doubleValue()));
        parch2.setNormalizer(new NumberNormalizer(testDataSetToHelp.minValue(parch2).doubleValue(), testDataSet.maxValue(parch2).doubleValue()));
        fare.setNormalizer(new NumberNormalizer(testDataSet.minValue(fare).doubleValue(), testDataSet.maxValue(fare).doubleValue()));
        fare2.setNormalizer(new NumberNormalizer(testDataSetToHelp.minValue(fare2).doubleValue(), testDataSet.maxValue(fare2).doubleValue()));
        gen.setNormalizer(new EnumNormalizer(Gender.class));
        gen2.setNormalizer(new EnumNormalizer(Gender.class));
        embarked.setNormalizer(new EnumNormalizer(Embarked.class));
        embarked2.setNormalizer(new EnumNormalizer(Embarked.class));
        survived.setNormalizer(new NullNormalizer());
        survived2.setNormalizer(new NullNormalizer());
        ticket.setNormalizer(new NullNormalizer());
        ticket2.setNormalizer(new NullNormalizer());
        name.setNormalizer(new NullNormalizer());
        name2.setNormalizer(new NullNormalizer());
        color.setNormalizer(new NullNormalizer());
        color2.setNormalizer(new NullNormalizer());

        Column[] colonnes = new Column[]{passengerId, survived, passengerClass, name, gen, age, sibSp, parch, ticket, cabin, fare, embarked, color};
        Column[] colonnes2 = new Column[]{passengerId2, survived2, passengerClass2, name2, gen2, age2, sibSp2, parch2, ticket2, cabin2, fare2, embarked2, color2};
        testDataSet.setColumns(colonnes);
        testDataSet.setColumnClass(survived);
        testDataSetToHelp.setColumnClass(survived2);
        testDataSet.setAllColors();
        testDataSetToHelp.setColumns(colonnes2);
        testDataSetToHelp.setAllColors();
        c = new Classifier(testDataSet, testDataSetToHelp);
    }

    /**
     * tests if classify changes the color of the point and gives it a class when it does not have one at the beginning
     */
    @Test
    void test_classify() {
        IPoint ird = testDataSet.getPoint(0);
        IPoint ird2 = testDataSet.getPoint(2);
        assertEquals(GenClass.FIRSTCLASS, ird.getPointGenClass());
        c.classify(ird, c.closeNeighbours(ird, 10, new EuclidianDistance()));
        ird. setRealClassFromGenClass();
        assertEquals(GenClass.FIRSTCLASS, ird.getPointGenClass());
        assertEquals(false, ird.getValue(survived));
        c.classify(ird2, c.closeNeighbours(ird2, 10, new EuclidianDistance()));
        ird. setRealClassFromGenClass();
        assertEquals(GenClass.SECONDCLASS, ird2.getPointGenClass());
        ird2. setRealClassFromGenClass();
        assertEquals(true, ird2.getValue(survived));
    }

    @Test
    void test_set_real_class_from_gen_class(){
        TitanicRawData ird = (TitanicRawData) testDataSet.getPoint(0);
        ird.setPointGenClass(GenClass.FIRSTCLASS);
        ird.setRealClassFromGenClass();
        assertFalse((Boolean) ird.getValue(survived));
    }

}

