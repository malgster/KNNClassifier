package Normalizers;

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
        enumClass.cast(m);
        return (m.ordinal())/ (this.getNumberOfElements()-1);
    }

    @Override
    public Object denormalize(Double value) {

        return this.castByOrdinal(value*(this.getNumberOfElements()-1));
    }

    private <E extends Enum> Enum castByOrdinal(Double ordinal){
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

    public double getNumberOfElements(){
        return EnumSet.allOf(enumClass).size();
    }

    public static void main(String[] args) {
        EnumNormalizer genderNorm = new EnumNormalizer(Gender.class);
        EnumNormalizer varietyNorm = new EnumNormalizer(IrisVariety.class);
        EnumNormalizer embarkedNorm = new EnumNormalizer(Embarked.class);

        Enum g = (Enum) genderNorm.denormalize(0.0);
        Enum v = (Enum) varietyNorm.denormalize(0.5);
        Enum e = (Enum) embarkedNorm.denormalize(0.5);
        System.out.println("gender 0 : " + g.name());
        System.out.println("variety 0.5 : " + v.name());
        System.out.println("embarked 0.5 : " + e.name());
        System.out.println("Normalization FEMALE : " + genderNorm.normalize(Gender.FEMALE));
        System.out.println("Normalization SETOSA : " + varietyNorm.normalize(IrisVariety.VERSICOLOR));
        System.out.println("Normalization embarked C : " + embarkedNorm.normalize(Embarked.Q));

    }

}
