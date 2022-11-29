package both;

import Normalizers.NullNormalizer;
import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;
import dataInterfaces.IValueNormalizer;

/***
 * generic class Column, represents a column from the dataset
 */
public class Column implements IColumn {

    private final String name;
    /**
     * column's name
     */
    private final DataSet itsDataSet;
    /**
     * column's source dataset
     */
    private IValueNormalizer normalizer;

    /**
     * column normalizer --> package Normalizers (Boolean, Enum, Number et Null)
     */

    public Column(final String name, DataSet itsDataSet, IValueNormalizer normalizer) {
        this.name = name;
        this.itsDataSet = itsDataSet;
        this.normalizer = normalizer;
    }

    public Column(final String name, DataSet itsDataSet) {
        this(name, itsDataSet, new NullNormalizer());
    }

    public IValueNormalizer getNormalizer() {
        return this.normalizer;
    }

    public String getNormalizerType() { return this.normalizer.stringValue(); }

    @Override
    public void setNormalizer(IValueNormalizer valueNormalizer) {
        this.normalizer = valueNormalizer;
    }

    /**
     * returns the normalized value of the column for the point
     * @param point
     * @return normalizedValue
     */
    @Override
    public double getNormalizedValue(IPoint point) {
        return normalizer.normalize(point.getValue(this));
    }

    /**
     * returns the column's value before the normalization
     * @param value
     * @return denormalizedValue
     */
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

    /**
     * @return true if the column has a normalizer (so if its normalizer is not a NullNormalizer)
     */
    @Override
    public boolean isNormalizable() {
        return !normalizer.getClass().equals(NullNormalizer.class);
    }

    /**
     * @return the column's minValue in the dataset
     */
    public double getMin() {
        double min = (double) itsDataSet.getValue(0, this);
        double temp;
        for (IPoint i : itsDataSet) {
            temp = (double) i.getValue(this);
            if (temp < min) min = temp;
        }
        return min;
    }

    /**
     * @return the column's maxValue in the dataset
     */
    public double getMax() {
        double max = (double) itsDataSet.getValue(0, this);
        double temp;
        for (IPoint i : itsDataSet) {
            temp = (double) i.getValue(this);
            if (temp > max) max = temp;
        }
        return max;
    }


}
