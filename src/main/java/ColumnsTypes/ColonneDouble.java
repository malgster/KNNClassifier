package ColumnsTypes;

import java.util.ArrayList;

import both.DataSet;
import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import iris.IrisRawData;

public class ColonneDouble implements IColumn, IValueNormalizer {
    private final String name;
    protected IDataSet doubleColDataSet;
    protected IValueNormalizer doubleColNormalizer;
    protected double min;
    protected double max;

    protected final TypeNormalizer boolColTypeNorm = TypeNormalizer.NUMBER_NORMALIZER;


    public ColonneDouble(final String name, IDataSet doubleColDataSet){
        this.name = name;
        this.doubleColDataSet = doubleColDataSet;
    }




    
    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
    	this.doubleColNormalizer = valueNormalizer;
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
        return doubleColDataSet;
    }

    @Override
    public boolean isNormalizable() {
        return doubleColNormalizer!=null ;
    }



	@Override
	public double normalize(Object value) {
		return ((Double)value-min)/(max-min);
	}



	@Override
	public Object denormalize(Double value) {
		return value*(max-min)+min;
	}
}
