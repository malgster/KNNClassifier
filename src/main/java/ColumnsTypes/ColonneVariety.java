package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;

public class ColonneVariety implements IColumn, IValueNormalizer{

	private final String name;
	protected IDataSet varietyColDataSet;
	protected IValueNormalizer varietyColNormalizer;

	protected final IValueNormalizer.TypeNormalizer varietyColTypeNorm = IValueNormalizer.TypeNormalizer.OTHER_NORMALIZER;


	public ColonneVariety(final String name, IDataSet intColDataSet, IValueNormalizer intColNormalizer){
		this.name = name;
		this.varietyColDataSet = intColDataSet;
		setNormalizer(intColNormalizer);

	}

	@Override
	public double normalize(Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double denormalize(Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNormalizer(IValueNormalizer valueNormalizer) {
		varietyColNormalizer=valueNormalizer;
	}

	@Override
	public double getNormalizedValue(IPoint point) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getDenormalizedValue(double value) {
		// TODO Auto-generated method stub
		return null;
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

}
