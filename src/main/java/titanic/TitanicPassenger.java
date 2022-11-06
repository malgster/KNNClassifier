package titanic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class TitanicPassenger implements IDataSet {




    // c moche ?
    private static List<TitanicRawData> myData;
    private static List<IPoint> myTitanicPoints;

    public static List<TitanicRawData> getMyData() {
		return myData;
	}


	public static void load(String fileName) {
    	try {
			System.out.println(fileName);
			myData=new CsvToBeanBuilder<TitanicRawData>(Files.newBufferedReader(Paths.get(fileName)))
					.withSeparator(',')
					.withType(TitanicRawData.class)
					.build().parse();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
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
        return null;
    }
    
    public static void main(String[] args) {
    	String fileName=Paths.get(".").normalize().toAbsolutePath()+File.separator+"ressources/titanic.csv";
    	TitanicPassenger.load(fileName);
		System.out.println(myData);
	}
}