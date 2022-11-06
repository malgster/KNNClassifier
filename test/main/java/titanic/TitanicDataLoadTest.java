package titanic;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class TitanicDataLoadTest {

String fileName=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/titanic.csv";
	
	@Test
	void test_load_csv_titanic_good_attributes() {
		TitanicPassenger.load(fileName);
		assertEquals(1, TitanicPassenger.getMyData().get(0).getPassengerId());
		assertEquals(true, TitanicPassenger.getMyData().get(1).hasSurvived());
		assertEquals(3, TitanicPassenger.getMyData().get(2).getPassengerClass());
		assertEquals("Futrelle, Mrs. Jacques Heath (Lily May Peel)", TitanicPassenger.getMyData().get(3).getName());
		assertEquals(Gender.MALE, TitanicPassenger.getMyData().get(4).getGen());
		assertEquals(0, TitanicPassenger.getMyData().get(5).getAge());
		assertEquals(0, TitanicPassenger.getMyData().get(6).getSibSp());
		assertEquals(1, TitanicPassenger.getMyData().get(7).getParch());
		assertEquals("STON/O2.3101282", TitanicPassenger.getMyData().get(2).getTicket());
		assertEquals(11.1333, TitanicPassenger.getMyData().get(8).getFare());
		assertEquals("", TitanicPassenger.getMyData().get(9).getCabin());
		assertEquals('S', TitanicPassenger.getMyData().get(10).getEmbarked());
	}

}
