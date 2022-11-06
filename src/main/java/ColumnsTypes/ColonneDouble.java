package ColumnsTypes;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;
import dataInterfaces.IValueNormalizer.TypeNormalizer;

public class ColonneDouble implements IColumn {
    private final String name;
    protected IDataSet doubleColDataSet;
    protected IValueNormalizer doubleColNormalizer;

    protected final TypeNormalizer boolColTypeNorm = TypeNormalizer.NUMBER_NORMALIZER;


    public ColonneDouble(final String name, IDataSet doubleColDataSet, IValueNormalizer doublecolNormalizer){
        this.name = name;
        this.doubleColDataSet = doubleColDataSet;
        setNormalizer(doublecolNormalizer);

    }



    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
    	this.doubleColNormalizer = valueNormalizer;
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
        return doubleColDataSet;
    }

    @Override
    public boolean isNormalizable() {
        return doubleColNormalizer!=null ;
    }
}
