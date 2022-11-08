package iris;

public enum IrisVariety {

    SETOSA(0.0), VERSICOLOR(0.5), VIRGINICA(1.0);

    double norm;
    private IrisVariety(double norm) {
        this.norm = norm;
    }
    
    public double getNorm() {
    	return norm;
    }
}
