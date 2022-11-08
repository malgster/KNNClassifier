package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;

public class ColonneString implements IColumn {
    private final String name;
    protected IDataSet stringColDataSet;
    protected IValueNormalizer stringColNormalizer;

	protected final IValueNormalizer.TypeNormalizer stringColTypeNorm = IValueNormalizer.TypeNormalizer.OTHER_NORMALIZER;


    public ColonneString(final String name, IDataSet intColDataSet){
        this.name = name;
        this.stringColDataSet = intColDataSet;
    }

	@Override
	public void setNormalizer(IValueNormalizer valueNormalizer) {
		this.stringColNormalizer=valueNormalizer;
	}

	@Override
	public double getNormalizedValue(IPoint point) {
		return -1;
	}

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
		return stringColDataSet;
	}

	@Override
	public boolean isNormalizable() {
		return stringColNormalizer!=null;
	}

}
