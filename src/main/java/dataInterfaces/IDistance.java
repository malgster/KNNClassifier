package dataInterfaces;

import both.DataSet;

@FunctionalInterface
public interface IDistance {
    double calculateDistance(IPoint o1, IPoint o2, DataSet ds1, DataSet ds2);
}

