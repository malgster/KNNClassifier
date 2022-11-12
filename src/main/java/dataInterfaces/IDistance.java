package dataInterfaces;

public interface IDistance {
    public double distanceEuclidienne(IPoint o1, IPoint o2, IDataSet ds);
    public double distanceManhatthan(IPoint o1, IPoint o2, IDataSet ds);
    public double distanceEuclidienneNormalisee(IPoint o1, IPoint o2, IDataSet ds);
    public double distanceManhatthanNormalisee(IPoint o1, IPoint o2, IDataSet ds);
    public void knnMethod();
}
