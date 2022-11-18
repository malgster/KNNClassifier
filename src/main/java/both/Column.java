package both;

import Normalizers.NullNormalizer;
import both.DataSet;
import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;

public class Column implements IColumn {

    private final String name;
    private  final DataSet itsDataSet;
    private IValueNormalizer normalizer;


    public Column (final String name, DataSet itsDataSet, IValueNormalizer normalizer) {
        this.name = name;
        this.itsDataSet = itsDataSet;
        this.normalizer = normalizer;
    }

    public Column(final String name, DataSet itsDataSet){
        this(name, itsDataSet, new NullNormalizer());
    }

    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
        this.normalizer = valueNormalizer;
    }
    
    public IValueNormalizer getNormalizer() {
        return this.normalizer;
    }

    @Override
    public double getNormalizedValue(IPoint point) {
        return normalizer.normalize(point.getValue(this));
    }

    @Override
    public Object getDenormalizedValue(double value) {
        return normalizer.denormalize(value);
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IDataSet getDataset() {
        return this.itsDataSet;
    }

    @Override
    public boolean isNormalizable() {
        return !normalizer.getClass().equals(NullNormalizer.class);
    }

    public double getMin() {
        double min = (double) itsDataSet.getMyPoints().get(0).getValue(this);
        double temp;
        for (IPoint i : itsDataSet) {
            temp = (double) i.getValue(this);
            if(temp < min) min = temp;
        }
        return min;
    }
    public double getMax(){
        double max = (double) itsDataSet.getMyPoints().get(0).getValue(this);
        double temp;
        for (IPoint i : itsDataSet) {
            temp = (double) i.getValue(this);
            if(temp > max) max = temp;
        }
        return max;
    }


}
