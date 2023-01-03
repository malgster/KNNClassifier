package iris;

import both.GenClass;
import com.opencsv.bean.CsvBindByName;
import dataInterfaces.IColumn;
import dataInterfaces.IPoint;


/**
 * an iris
 */
public class IrisRawData implements IPoint {
    //the attributes from the csv
    @CsvBindByName(column = "sepal.length")
    protected double sepalLength;
    @CsvBindByName(column = "sepal.width")
    protected double sepalWidth;
    @CsvBindByName(column = "petal.length")
    protected double petalLength;
    @CsvBindByName(column = "petal.width")
    protected double petalWidth;
    @CsvBindByName(column = "variety")
    protected IrisVariety variety;
    protected GenClass pointGenClass = GenClass.NULL;

    public IrisRawData(double sL, double sW, double pL, double pW, IrisVariety variety) {
        sepalLength = sL;
        sepalWidth = sW;
        petalLength = pL;
        petalWidth = pW;
        this.variety = variety;
    }

    public IrisRawData() {
    }


    /**
     * gets the value of the point for a column given as a parameter
     * @param col
     * @return point's value for the given attribute
     */
    @Override
    public Object getValue(IColumn col) {
        switch (col.getName().toLowerCase()) {
            case "sepallength":
                return sepalLength;
            case "sepalwidth":
                return sepalWidth;
            case "petallength":
                return petalLength;
            case "petalwidth":
                return petalWidth;
            case "variety":
                return variety;
            case "color":
                return pointGenClass;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return sepalLength + ", " + sepalWidth + ", " + petalLength + ", " + petalWidth + ", " + variety;
    }

    /**
     * gets a point's normalized value for the column given in parameters
     * @param xcol
     * @return normalized value
     */
    @Override
    public double getNormalizedValue(IColumn xcol) {
        return xcol.getNormalizedValue(this);
    }

    /**
     * set the color given the class value
     */
    @Override
    public void setPointGenClass() {
        if (pointGenClass != GenClass.NULL) return;
        pointGenClass = (this.variety != null) ? GenClass.values()[variety.ordinal()] : GenClass.NULL;
    }

    public GenClass getPointGenClass() {
        return this.pointGenClass;
    }

    @Override
    public void setPointGenClass(GenClass cl) {
        if (this.pointGenClass == GenClass.NULL) this.pointGenClass = cl;
    }

    /**
     * sets the class value given the point's color
     */
    @Override
    public void setRealClassFromGenClass() {
        variety = (this.pointGenClass != GenClass.NULL) ? IrisVariety.values()[pointGenClass.ordinal()] : null;
    }

    @Override
    public GenClass getPointClass() {
        return pointGenClass;
    }

}
