package Distances;

import Normalizers.EnumNormalizer;
import both.DataSet;
import dataInterfaces.IDistance;
import dataInterfaces.IPoint;
import utils.Format;

import java.math.RoundingMode;

/**
 * calculates a manhattan distance given two points and their dataset
 */
public class ManhatthanDistance implements IDistance {

    @Override
    public double calculateDistance(IPoint o1, IPoint o2, DataSet dsToClassify , DataSet baseDs) {
        double res = 0;
        for (int i=0; i<dsToClassify.getColumnsWithoutClass().size(); i++) {
            res += (EnumNormalizer.valueIsEnum(o1, dsToClassify.getColumn(i))) ?
                    EnumNormalizer.finalNormalisation(o1, o2, dsToClassify.getColumn(i)) :
                    Math.abs(o1.getNormalizedValue(dsToClassify.getColumn(i)) - o2.getNormalizedValue(baseDs.getColumn(i)));
        }
        return Format.formatDouble(res, 3, RoundingMode.FLOOR);
    }
}
