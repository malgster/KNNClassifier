package Distances;

import Normalizers.EnumNormalizer;
import Normalizers.NumberNormalizer;
import both.Column;
import both.CsvLoader;
import both.DataSet;
import dataInterfaces.IColumn;
import dataInterfaces.IDistance;
import dataInterfaces.IPoint;
import iris.IrisRawData;
import iris.IrisVariety;
import utils.Format;

import java.io.File;
import java.math.RoundingMode;
import java.nio.file.Paths;

public class EuclidianDistance implements IDistance {



    @Override
    public double calculateDistance(IPoint o1, IPoint o2, DataSet ds) {
        double res = 0;
        for (IColumn col : ds.getColumns()) {
            res += (EnumNormalizer.valueIsEnum(o1, col)) ?
                    EnumNormalizer.finalNormalisation(o1, o2, col) :
                    Math.pow(o1.getNormalizedValue(col) - o2.getNormalizedValue(col), 2);
            }
        return  Format.formatDouble(Math.sqrt(res),3, RoundingMode.FLOOR);
    }


}
