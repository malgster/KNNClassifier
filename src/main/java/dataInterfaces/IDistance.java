package dataInterfaces;

public interface IDistance {
    public double distanceEuclidienne(IPoint o1, IPoint o2);
    public double distanceManhatthan(IPoint o1, IPoint o2);
    public void knnMethod();
}
