package ColumnsTypes;

import java.util.List;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import iris.IrisRawData;
import iris.IrisVariety;

public class ColonneBoolean implements IColumn, IValueNormalizer {
    private final String name;
    protected IDataSet boolColDataSet;
    protected IValueNormalizer boolColNormalizer;
    protected final TypeNormalizer boolColTypeNorm = TypeNormalizer.BOOLEAN_NORMALIZER;




    public ColonneBoolean(final String name, IDataSet boolColDataSet, IValueNormalizer boolcolNormalizer){
        this.name = name;
        this.boolColDataSet = boolColDataSet;
        setNormalizer(boolcolNormalizer);
    }

    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
        this.boolColNormalizer = valueNormalizer;
    }

    // TODO
    @Override
    public double getNormalizedValue(IPoint point) {
        return normalize(point);
    }

    // TODO
    @Override
    public Object getDenormalizedValue(double value) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IDataSet getDataset() {
        return boolColDataSet;
    }

    @Override
    public boolean isNormalizable() {
    	return boolColNormalizer!=null;
    }

    @Override
    public double normalize(Object value) {
        if(((Boolean)value)==true) {
        	return 1;
        }
        return 0;
    }

    // TODO
    @Override
    public double denormalize(Object value) {
    	return 0;
    }
}
