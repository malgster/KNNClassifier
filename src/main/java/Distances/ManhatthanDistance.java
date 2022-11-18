package Distances;

import Normalizers.EnumNormalizer;
import both.DataSet;
import dataInterfaces.IColumn;
import dataInterfaces.IDistance;
import dataInterfaces.IPoint;
import utils.Format;

import java.math.RoundingMode;

public class ManhatthanDistance implements IDistance {

    @Override
    public double calculateDistance(IPoint o1, IPoint o2, DataSet ds) {
        double res = 0;
        for (IColumn col : ds.getColumns()) {
                res += (EnumNormalizer.valueIsEnum(o1, col)) ?
                    EnumNormalizer.finalNormalisation(o1, o2, col) :
                    Math.abs(o1.getNormalizedValue(col) - o2.getNormalizedValue(col));
            }
        return Format.formatDouble(res, 3, RoundingMode.FLOOR);
    }
}
