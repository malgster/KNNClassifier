package Normalizers;

import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import iris.IrisVariety;
import titanic.Embarked;
import titanic.Gender;

import java.util.EnumSet;
import java.util.Iterator;

public class EnumNormalizer implements IValueNormalizer {

    private final Class<? extends Enum> enumClass;

    public EnumNormalizer(final Class<? extends  Enum> enumClass){
        this.enumClass = enumClass;
    }


    @Override
    public double normalize(Object value) {
        Enum m = ((Enum) value);
        return (m.ordinal())/ (this.getNumberOfElements()-1);
    }

    @Override
    public Object denormalize(Double value) {

        return this.castByOrdinal(value*(this.getNumberOfElements()-1));
    }

    private  <E extends Enum> Enum castByOrdinal(Double ordinal){
        final Iterator<E> iter = EnumSet.allOf(enumClass).iterator();
        int count = 0;
        E eValue = null;
        while (count <= ordinal.intValue()) {
            if (!iter.hasNext()) {
                return null;
            }
            eValue = iter.next();
            count++;
        }
        return eValue;
    }

    public final double getNumberOfElements(){
        return EnumSet.allOf(enumClass).size();
    }

    private static final boolean areEqualsEnums(IPoint o1, IPoint o2, IColumn col) {
            return o1.getValue(col).equals(o2.getValue(col));
    }
    public static final boolean valueIsEnum(IPoint i, IColumn col) {
        return i.getValue(col).getClass().isEnum();
    }

    public static double finalNormalisation(IPoint o1, IPoint o2, IColumn col) {
        return areEqualsEnums(o1, o2, col) ? 0 : 1;
    }
}
