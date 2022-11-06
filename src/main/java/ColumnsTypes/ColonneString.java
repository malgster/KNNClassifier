package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;

public class ColonneString implements IColumn {
    private final String name;
    protected IDataSet stringColDataSet;
    protected IValueNormalizer stringColNormalizer;


    public ColonneString(final String name, IDataSet intColDataSet, IValueNormalizer intColNormalizer){
        this.name = name;
        this.stringColDataSet = intColDataSet;
        setNormalizer(intColNormalizer);

    }

	@Override
	public void setNormalizer(IValueNormalizer valueNormalizer) {
		this.stringColNormalizer=valueNormalizer;
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
		return stringColDataSet;
	}

	@Override
	public boolean isNormalizable() {
		return stringColNormalizer!=null;
	}

}
