package both;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataInterfaces.IPoint;
import titanic.Gender;
import titanic.TitanicRawData;

class ColumnTitanicTest {

	String fileName=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/titanic.csv";
	DataSet testDataSet = new DataSet("TitanicSet", (Class<? extends IPoint>) TitanicRawData.class);
	Column survivedCol;
	Column iDCol;
	Column nameCol;
	Column ageCol;
	Column genreCol ;
	Column sibSpCol;
	Column ticketCol;
	Column parchCol;
	Column cabinCol;
	Column fareCol;
	Column embarkedCol;


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
		 embarkedCol = new Column(("embarked"), testDataSet);
	}


	@Test
	void test_load_csv_titanic_attributes(){

		assertEquals(true, testDataSet.getMyPoints().get(1).getValue(survivedCol));
		assertEquals(3, testDataSet.getMyPoints().get(2).getValue(iDCol));
		assertEquals("Futrelle, Mrs. Jacques Heath (Lily May Peel)", testDataSet.getMyPoints().get(3).getValue(nameCol));
		assertEquals(Gender.MALE, testDataSet.getMyPoints().get(4).getValue(genreCol));
		assertEquals(0.0, testDataSet.getMyPoints().get(5).getValue(ageCol));
		assertEquals(0, testDataSet.getMyPoints().get(6).getValue(sibSpCol));
		assertEquals(1, testDataSet.getMyPoints().get(7).getValue(parchCol));
		assertEquals("STON/O2.3101282", testDataSet.getMyPoints().get(2).getValue(ticketCol));
		assertEquals(11.1333, testDataSet.getMyPoints().get(8).getValue(fareCol));
		assertEquals("", testDataSet.getMyPoints().get(9).getValue(cabinCol));
		assertEquals('S', testDataSet.getMyPoints().get(10).getValue(embarkedCol));
	}

}