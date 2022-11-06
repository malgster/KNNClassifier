package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;

public class ColonneGenre implements IColumn {

    private String name;
    protected IDataSet genreColDataSet;
    protected IValueNormalizer genreColNormalizer;

    protected final IValueNormalizer.TypeNormalizer intColTypeNorm = IValueNormalizer.TypeNormalizer.OTHER_NORMALIZER;
    
    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
        this.genreColNormalizer = valueNormalizer;
    }

    // TODO
    @Override
    public double getNormalizedValue(IPoint point) {
        return 0;
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
        return genreColDataSet;
    }

    @Override
    public boolean isNormalizable() {
    	return genreColNormalizer!=null;
    }
}
