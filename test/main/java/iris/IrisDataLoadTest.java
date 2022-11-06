package iris;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class IrisDataLoadTest {

	String fileName=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/iris.csv";
	
	@Test
	void test_load_csv_iris_good_attributes() {
		Iris.load(fileName);
		assertEquals(1.4, Iris.getMyData().get(0).getPetalLength());
		assertEquals(4.9, Iris.getMyData().get(1).getSepalLength());
		assertEquals(0.2, Iris.getMyData().get(2).getPetalWidth());
		assertEquals(3.6, Iris.getMyData().get(4).getSepalWidth());
		assertEquals(IrisVariety.SETOSA, Iris.getMyData().get(3).getVariety());
	}

}
