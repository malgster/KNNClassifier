package Distances;

import Normalizers.EnumNormalizer;
import both.DataSet;
import dataInterfaces.IDistance;
import dataInterfaces.IPoint;
import utils.Format;

import java.math.RoundingMode;

/**
 * an euclidian distance used in the classifier which calculates a distance given 2 points and their datasets
 */
public class EuclidianDistance implements IDistance {

    @Override
    public double calculateDistance(IPoint o1, IPoint o2, DataSet dsToClassify, DataSet ds2) {
        double res = 0;
        for (int i=0; i<dsToClassify.getColumnsWithoutClass().size(); i++) {
            res += (EnumNormalizer.valueIsEnum(o1, dsToClassify.getColumn(i))) ?
                    EnumNormalizer.finalNormalisation(o1, o2, dsToClassify.getColumn(i)) :
                    Math.pow(o1.getNormalizedValue(dsToClassify.getColumn(i)) - o2.getNormalizedValue(ds2.getColumn(i)), 2);
        }
        return Format.formatDouble(Math.sqrt(res), 3, RoundingMode.FLOOR);
    }


}
