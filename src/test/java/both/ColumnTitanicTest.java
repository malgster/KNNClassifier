package both;

import dataInterfaces.IPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import titanic.Embarked;
import titanic.Gender;
import titanic.TitanicRawData;
import utils.CsvLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * tests the attribute values of titanic
 */
class ColumnTitanicTest {

    String fileName = Paths.get(".").normalize().toAbsolutePath() + File.separator + "src/main/resources/datasets/titanic.csv";
    DataSet testDataSet = new DataSet("TitanicSet", TitanicRawData.class);
    Column survivedCol;
    Column iDCol;
    Column nameCol;
    Column ageCol;
    Column genreCol;
    Column sibSpCol;
    Column ticketCol;
    Column parchCol;
    Column cabinCol;
    Column fareCol;
    Column embarkedCol;
    Column color;
    Column nulle;

    /**
     * sets up the dataset, columns, normalizers and classifier
     */
    @BeforeEach
    void setUp() {
        testDataSet.setLines((List<IPoint>) CsvLoader.load(fileName, TitanicRawData.class));
        survivedCol = new Column("survived", testDataSet);
        iDCol = new Column("passengerId", testDataSet);
        nameCol = new Column("name", testDataSet);
        ageCol = new Column("age", testDataSet);
        genreCol = new Column("gen", testDataSet);
        sibSpCol = new Column("sibSp", testDataSet);
        ticketCol = new Column("ticket", testDataSet);
        parchCol = new Column("parch", testDataSet);
        cabinCol = new Column("cabin", testDataSet);
        fareCol = new Column("fare", testDataSet);
        embarkedCol = new Column("embarked", testDataSet);
        color = new Column("color", testDataSet);
        nulle = new Column("nulle", testDataSet);
    }


    /**
     * tests the getValues
     */
    @Test
    void test_load_csv_titanic_attributes() {

        assertEquals(true, testDataSet.getPoint(1).getValue(survivedCol));
        assertEquals(3, testDataSet.getPoint(2).getValue(iDCol));
        assertEquals("Futrelle, Mrs. Jacques Heath (Lily May Peel)", testDataSet.getPoint(3).getValue(nameCol));
        assertEquals(Gender.MALE, testDataSet.getPoint(4).getValue(genreCol));
        assertEquals(0.0, testDataSet.getPoint(5).getValue(ageCol));
        assertEquals(0, testDataSet.getPoint(6).getValue(sibSpCol));
        assertEquals(1, testDataSet.getPoint(7).getValue(parchCol));
        assertEquals("STON/O2.3101282", testDataSet.getPoint(2).getValue(ticketCol));
        assertEquals(11.1333, testDataSet.getPoint(8).getValue(fareCol));
        assertEquals("", testDataSet.getPoint(9).getValue(cabinCol));
        assertEquals(Embarked.S, testDataSet.getPoint(10).getValue(embarkedCol));
    }

    /**
     * tests a column that does not exist in the csv
     */
    @Test
    void test_colonne_non_existante() {
        assertNull(testDataSet.getPoint(0).getValue(nulle));
        assertNull(testDataSet.getColumn("nulle"));
    }

}