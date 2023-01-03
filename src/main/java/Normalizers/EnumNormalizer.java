package Normalizers;

import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import titanic.Embarked;
import utils.Format;

import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * normalizers set to enum Columns(embarked for example)
 */
public class EnumNormalizer implements IValueNormalizer {

    private final Class<? extends Enum> ENUMCLASS;

    public EnumNormalizer(final Class<? extends Enum> enumClass) {
        this.ENUMCLASS = enumClass;
    }


    public static final boolean valueIsEnum(IPoint i, IColumn col) {
        return i.getValue(col).getClass().isEnum();
    }

    public static double finalNormalisation(IPoint o1, IPoint o2, IColumn col) {
        return (o1.getNormalizedValue(col) - o2.getNormalizedValue(col) == 0 ) ? 0 : 1;
    }

    @Override
    public double normalize(Object value) {
        Enum m = ((Enum) value);
        return Format.formatDouble((m.ordinal()) / (this.getNumberOfElements() - 1), 1, RoundingMode.DOWN);
    }

    @Override
    public Object denormalize(Double value) {
        return this.castByOrdinal(value * (this.getNumberOfElements() - 1));
    }

    @Override
    public String stringValue() {
        return "enum";
    }

    private <E extends Enum> Enum castByOrdinal(Double ordinal) {
        final Iterator<E> iter = EnumSet.allOf(ENUMCLASS).iterator();
        int count = 0;
        E eValue = null;
        while (count <= ordinal.intValue()) {
            if (!iter.hasNext()) {
                return Embarked.NULL;
            }
            eValue = iter.next();
            count++;
        }
        return eValue;
    }

    public final double getNumberOfElements() {
        return EnumSet.allOf(ENUMCLASS).size();
    }

}
