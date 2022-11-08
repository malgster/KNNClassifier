package ColumnsTypes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import titanic.TitanicDataSet;

class ColonneBooleanTest {

	String fileName=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/titanic.csv";

	@Test
	void test_normalize_boolean_column() {
		TitanicDataSet.load(fileName);
		boolean survived= TitanicDataSet.getMyTitanicData().get(0).isSurvived();
		boolean survived2= TitanicDataSet.getMyTitanicData().get(2).isSurvived();
		ColonneBoolean col=new ColonneBoolean("survived", new TitanicDataSet());
		assertEquals(0, col.normalize(survived));
		assertEquals(1, col.normalize(survived2));
	}
}
