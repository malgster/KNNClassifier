package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import titanic.Gender;

public class ColonneGenre implements IColumn, IValueNormalizer {

    private String name;
    protected IDataSet genreColDataSet;
    protected IValueNormalizer genreColNormalizer;

    protected final IValueNormalizer.TypeNormalizer intColTypeNorm = IValueNormalizer.TypeNormalizer.OTHER_NORMALIZER;
    
    public ColonneGenre(String name, IDataSet genreColDataSet) {
		this.name = name;
		this.genreColDataSet = genreColDataSet;
	}

	@Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
        this.genreColNormalizer = valueNormalizer;
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
        return genreColDataSet;
    }

    @Override
    public boolean isNormalizable() {
    	return genreColNormalizer!=null;
    }

	@Override
	public double normalize(Object value) {
		return ((Gender) value).getNorm();
	}

	@Override
	public Object denormalize(Double value) {
		if (value==1) return Gender.MALE;
		return Gender.FEMALE;
	}
}
