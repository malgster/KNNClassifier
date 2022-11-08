package iris;

import both.DataSet;
import com.opencsv.bean.CsvBindByName;

import dataInterfaces.IColumn;
import dataInterfaces.IPoint;


public class IrisRawData implements IPoint {


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

	public String toString() {
		return "Iris: sepal length = "+sepalLength+", sepalWidth = "+sepalWidth+", petalLength = "+petalLength+", petalWidth = "+petalWidth+", variety = "+variety;
	}

	public double getSepalLength() {
		return this.sepalLength;
	}
	public double getSepalWidth() {
		return this.sepalWidth;
	}
	public double getPetalLength() {
		return this.petalLength;
	}
	public double getPetalWidth() {
		return this.petalWidth;
	}
	
	public IrisVariety getVariety() {
		return this.variety;
	}

	@Override
	public Object getValue(IColumn col) {
		switch(col.getName()) {
		case "sepalLength":
			return getSepalLength();
		case "sepalWidth":
			return getSepalWidth();
		case "petalLength":
			return getPetalLength();
		case "petalWitdh":
			return getPetalWidth();
		case "variety":
			return getVariety();
		default:
			return null;
		}
	}

	@Override
	public double getNormalizedValue(IColumn xcol) {
		return xcol.getNormalizedValue(this);
	}
}



