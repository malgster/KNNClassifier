package iris;

public enum IrisVariety {

    SETOSA(1), VERSICOLOR(2), VIRGINICA(3);

    int norm;
    private IrisVariety(int norm) {
        this.norm = norm;
    }
    
    public int getNorm() {
    	return norm;
    }
}
