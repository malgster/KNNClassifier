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




    public ColonneBoolean(final String name, IDataSet boolColDataSet){
        this.name = name;
        this.boolColDataSet = boolColDataSet;
    }

    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
        this.boolColNormalizer = valueNormalizer;
    }

    // TODO
    @Override
    public double getNormalizedValue(IPoint point) {
        return normalize(point.getValue(this));
    }

    // TODO
    @Override
    public Object getDenormalizedValue(double value) {
        return denormalize(value);
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

	@Override
	public Object denormalize(Double value) {
		if(value==1) {
			return true;
		}
		return false;
	}


}
