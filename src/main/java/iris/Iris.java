package iris;

import com.opencsv.bean.CsvToBeanBuilder;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

// lambok utils
@Getter
@Setter
@EqualsAndHashCode

public class Iris implements IDataSet {

    private static List<IrisRawData> myData;
    private static List<IPoint> myIrisPoints;


    public static void load(String fileName) {
        try {
            System.out.println(fileName);
            myData=new CsvToBeanBuilder<IrisRawData>(Files.newBufferedReader(Paths.get(fileName)))
                    .withSeparator(',')
                    .withType(IrisRawData.class)
                    .build().parse();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<IrisRawData> getMyData() {
        return myData;
    }

    @Override
    public String getTitle() {
        return "Iris";
    }

    @Override
    public int getNbLines() {
        return myIrisPoints.size();
    }

    @Override
    public void setLines(List<IPoint> lines) {
        myIrisPoints= lines;
    }

    @Override
    public void addLine(IPoint element) {
        myIrisPoints.add(element);
    }

    @Override
    public void addAllLine(List<IPoint> elements) {
        myIrisPoints.addAll(elements);
    }

    @Override
    public Iterator<IPoint> iterator() {
        return myIrisPoints.iterator();
    }

    public static void main(String[] args) {
        String fileName=Paths.get(".").normalize().toAbsolutePath()+ File.separator+"ressources/iris.csv";
        Iris.load(fileName);
        System.out.println(myData);
    }


}
