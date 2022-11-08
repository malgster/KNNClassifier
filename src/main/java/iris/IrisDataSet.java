package iris;

import both.DataSet;
import com.opencsv.bean.CsvToBeanBuilder;
import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;


public class IrisDataSet extends DataSet {


    // à tout moment cette classe saute aussi (dsl léa)
    public IrisDataSet(String name, Class<? extends IPoint> pointClass, IColumn... columns) {
        super(name, pointClass, columns);
    }
}






