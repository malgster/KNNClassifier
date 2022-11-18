package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import iris.IrisVariety;

public class ColonneVariety implements IColumn, IValueNormalizer{

	private final String name;
	protected IDataSet varietyColDataSet;
	protected IValueNormalizer varietyColNormalizer;

	protected final IValueNormalizer.TypeNormalizer varietyColTypeNorm = IValueNormalizer.TypeNormalizer.OTHER_NORMALIZER;


	public ColonneVariety(final String name, IDataSet intColDataSet){
		this.name = name;
		this.varietyColDataSet = intColDataSet;

	}

	@Override
	public double normalize(Object value) {
		return ((IrisVariety) value).getNorm();
	}


	@Override
	public void setNormalizer(IValueNormalizer valueNormalizer) {
		varietyColNormalizer=valueNormalizer;
	}

	@Override
	public double getNormalizedValue(IPoint point) {
    	return normalize(point.getValue(this));
	}

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
		return varietyColDataSet;
	}

	@Override
	public boolean isNormalizable() {
		return varietyColNormalizer!=null;
	}

	@Override
	public Object denormalize(Double value) {
		if (value==0) return IrisVariety.SETOSA;
		if (value==0.5) return IrisVariety.VERSICOLOR;
		if (value==1) return IrisVariety.VIRGINICA;
		return null;
	}

}
