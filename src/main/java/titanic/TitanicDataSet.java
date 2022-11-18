package titanic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import both.DataSet;
import com.opencsv.bean.CsvToBeanBuilder;

import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class TitanicDataSet implements IDataSet {


    // à tout moment cette classe saute
    private static List<TitanicRawData> myTitanicData;
    private static List<IPoint> myTitanicPoints;


    
    @Override
    public String getTitle() {
        return "Titanic";
    }

    @Override
    public int getNbLines() {
        return myTitanicPoints.size();
    }

    @Override
    public void setLines(List<IPoint> lines) {
    	myTitanicPoints=lines;
    }

    @Override
    public void addLine(IPoint element) {
    	myTitanicPoints.add(element);
    }

    @Override
    public void addAllLine(List<IPoint> element) {
    	myTitanicPoints.addAll(element);
    }



    @Override
    public Iterator<IPoint> iterator() {
        return myTitanicPoints.iterator();
    }

    public static List<TitanicRawData> getMyTitanicData() {
        return myTitanicData;
    }

    public static void setMyTitanicData(List<TitanicRawData> myTitanicData) {
        TitanicDataSet.myTitanicData = myTitanicData;
    }

}