package ColumnsTypes;

import java.util.ArrayList;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import iris.IrisRawData;

public class ColonneInt implements IColumn, IValueNormalizer {
    private final String name;
    protected IDataSet intColDataSet;
    protected IValueNormalizer intColNormalizer;
    protected int min;
    protected int max;

    protected final IValueNormalizer.TypeNormalizer intColTypeNorm = IValueNormalizer.TypeNormalizer.NUMBER_NORMALIZER;


    public ColonneInt(final String name, IDataSet intColDataSet){
        this.name = name;
        this.intColDataSet = intColDataSet;
        setNormalizer(intColNormalizer);
        this.min=min();
        this.max=max();
    }

    public int min() {
    	ArrayList<Integer> values=new ArrayList<Integer>();
    	for(IPoint i: intColDataSet) {
    		values.add((Integer) i.getValue(this));
    	}
    	int min=values.get(0);
    	for(int v:values) {
    		if (v<min) {
    			min=v;
    		}
    	}
    	return min;
    }
    
    public int max() {
    	ArrayList<Integer> values=new ArrayList<Integer>();
    	for(IPoint i: intColDataSet) {
    		values.add((Integer) i.getValue(this));
    	}
    	int max=values.get(0);
    	for(int v:values) {
    		if (v>max) {
    			max=v;
    		}
    	}
    	return max;
    }

    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
            this.intColNormalizer = valueNormalizer;
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
        return intColDataSet;
    }

    @Override
    public boolean isNormalizable() {
    	return intColNormalizer!=null;
    }

	@Override
	public double normalize(Object value) {
		return ((Integer)value-min)/(max-min);
	}

	@Override
	public Object denormalize(Double value) {
		return value*(max-min)+min;
	}
}
