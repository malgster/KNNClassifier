package both;

import com.opencsv.bean.CsvToBeanBuilder;
import dataInterfaces.IPoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class CsvLoader {

    public static List<? extends IPoint> load(String fileName, Class<? extends IPoint> cls) {
        List<? extends IPoint> result = new LinkedList<>();
        try {
            System.out.println(fileName);
            result=new CsvToBeanBuilder<IPoint>(Files.newBufferedReader(Paths.get(fileName)))
                    .withSeparator(',')
                    .withType(cls)
                    .build().parse();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
