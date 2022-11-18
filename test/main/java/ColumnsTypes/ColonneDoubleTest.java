package ColumnsTypes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import titanic.TitanicDataSet;

class ColonneDoubleTest {
	String fileNameIris=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/iris.csv";
	String fileNameTitanic=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/titanic.csv";

	@Test
	void test_normalize_double_column_versTitanic(){
		TitanicDataSet.load(fileNameTitanic);
		double length1= TitanicDataSet.getMyTitanicData().get(0).getAge();
		double length2= TitanicDataSet.getMyTitanicData().get(2).getAge();
		ColonneDouble col=new ColonneDouble("Age", new TitanicDataSet());
		assertEquals(0, col.normalize(length1));
	}
	/*@Test
	void test_normalize_double_column() {
		Iris.load(fileNameTitanic);
		double length1=Iris.getMyData().get(0).getPetalLength();
		double length2=Iris.getMyData().get(1).getPetalLength();
		ColonneDouble col=new ColonneDouble("petalLength", new Iris());
		assertEquals(0, col.normalize(length1));
	}*/

}
