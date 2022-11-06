package both;

import dataInterfaces.IDistance;
import iris.IrisRawData;
import titanic.TitanicRawData;

public class Classifier implements IDistance{

    @Override
    public double distanceEuclidienne(Object o1, Object o2) {
    	if(o1 instanceof IrisRawData) {
    		IrisRawData iris1=(IrisRawData) o1; 
    		IrisRawData iris2=(IrisRawData) o2; 
    	} else if(o1 instanceof TitanicRawData) {
    		TitanicRawData passager1=(TitanicRawData) o1; 
    		TitanicRawData passager2=(TitanicRawData) o2; 
    	}
    	return 0;
    }

    @Override
    public double distanceManhatthan(Object o1, Object o2) {
    	if(o1 instanceof IrisRawData) {
    		IrisRawData iris1=(IrisRawData) o1; 
    		IrisRawData iris2=(IrisRawData) o2; 
    	} else if(o1 instanceof TitanicRawData) {
    		TitanicRawData passager1=(TitanicRawData) o1; 
    		TitanicRawData passager2=(TitanicRawData) o2; 
    	}
    	return 0;
    }

    @Override
    public void knnMethod() {

    }
}
