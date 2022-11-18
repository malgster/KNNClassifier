package both;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import titanic.TitanicRawData;

class DataSetTitanicTest {

    String fileName= Paths.get(".").normalize().toAbsolutePath()+ File.separator+"ressources/titanic.csv";
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
	void setUp(){
		testDataSet.setMyPoints(CsvLoader.load(fileName, TitanicRawData.class));
		passengerId=new Column("passengerid", testDataSet);
		survived=new Column("survived", testDataSet);
		passengerClass=new Column("passengerclass", testDataSet);
		name=new Column("name", testDataSet);
		gen=new Column("gen", testDataSet);
		age=new Column("age", testDataSet);
		sibSp=new Column("sibSp", testDataSet);
		parch=new Column("parch", testDataSet);
		ticket=new Column("ticket", testDataSet);
		cabin=new Column("cabin", testDataSet);
		fare=new Column("fare", testDataSet);
		embarked=new Column("embarked", testDataSet);
	}
    
	@Test
	void test_min_max_value() {
		assertEquals(0.0, age.getMin());
		assertEquals(80.0, age.getMax());
	}

}
