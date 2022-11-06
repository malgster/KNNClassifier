package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;

public class ColonneInt implements IColumn {
    private final String name;
    protected IDataSet intColDataSet;
    protected IValueNormalizer intColNormalizer;

    protected final IValueNormalizer.TypeNormalizer intColTypeNorm = IValueNormalizer.TypeNormalizer.NUMBER_NORMALIZER;


    public ColonneInt(final String name, IDataSet intColDataSet, IValueNormalizer intColNormalizer){
        this.name = name;
        this.intColDataSet = intColDataSet;
        setNormalizer(intColNormalizer);

    }

    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
            this.intColNormalizer = valueNormalizer;
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
        return intColDataSet;
    }

    @Override
    public boolean isNormalizable() {
    	return intColNormalizer!=null;
    }
}
