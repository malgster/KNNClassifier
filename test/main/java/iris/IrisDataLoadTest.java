package iris;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import both.CsvLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IrisDataLoadTest {

	String fileName=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/iris.csv";
	
	@Test
	void test_load_csv_iris_good_attributes() {
		IrisDataSet.setMyIrisData((List<IrisRawData>) CsvLoader.load(fileName, IrisRawData.class));
		assertEquals(1.4, IrisDataSet.getMyIrisData().get(0).getPetalLength());
		assertEquals(4.9, IrisDataSet.getMyIrisData().get(1).getSepalLength());
		assertEquals(0.2, IrisDataSet.getMyIrisData().get(2).getPetalWidth());
		assertEquals(3.6, IrisDataSet.getMyIrisData().get(4).getSepalWidth());
		assertEquals(IrisVariety.SETOSA, IrisDataSet.getMyIrisData().get(3).getVariety());
	}

}
