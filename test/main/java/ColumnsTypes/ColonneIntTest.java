package main.java.ColumnsTypes;

import org.junit.jupiter.api.Test;
import titanic.TitanicDataSet;

import java.io.File;
import java.nio.file.Paths;

public class ColonneIntTest {

    String fileName= Paths.get(".").normalize().toAbsolutePath()+ File.separator+"ressources/titanic.csv";

    @Test
    void test_normalize_int_column() {
        TitanicDataSet.load(fileName);

    }

}
