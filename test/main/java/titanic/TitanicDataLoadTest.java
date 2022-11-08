package titanic;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import both.CsvLoader;
import iris.IrisDataSet;
import iris.IrisRawData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TitanicDataLoadTest {

String fileName=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/titanic.csv";
	
	@Test
	void test_load_csv_titanic_good_attributes() {
		TitanicDataSet.setMyTitanicData((List<TitanicRawData>) CsvLoader.load(fileName, TitanicRawData.class));
		assertEquals(1, TitanicDataSet.getMyTitanicData().get(0).passengerId);
		assertEquals(true, TitanicDataSet.getMyTitanicData().get(1).survived);
		assertEquals(3, TitanicDataSet.getMyTitanicData().get(2).passengerClass);
		assertEquals("Futrelle, Mrs. Jacques Heath (Lily May Peel)", TitanicDataSet.getMyTitanicData().get(3).name);
		assertEquals(Gender.MALE, TitanicDataSet.getMyTitanicData().get(4).gen);
		assertEquals(0, TitanicDataSet.getMyTitanicData().get(5).age);
		assertEquals(0, TitanicDataSet.getMyTitanicData().get(6).sibSp);
		assertEquals(1, TitanicDataSet.getMyTitanicData().get(7).parch);
		assertEquals("STON/O2.3101282", TitanicDataSet.getMyTitanicData().get(2).ticket);
		assertEquals(11.1333, TitanicDataSet.getMyTitanicData().get(8).fare);
		assertEquals("", TitanicDataSet.getMyTitanicData().get(9).cabin);
		assertEquals('S', TitanicDataSet.getMyTitanicData().get(10).embarked);
	}

}
