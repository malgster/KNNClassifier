package both;

import dataInterfaces.IDistance;
import dataInterfaces.IPoint;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classifieur des données fournit selon l'algorithme d'apprentissage KNN
 */
public class Classifier {

    private final DataSet dsToClassify;

    private final DataSet baseDataSet;


    public Classifier(DataSet dsToClassify, DataSet baseDataSet) {
        this.dsToClassify = dsToClassify;
        this.baseDataSet = baseDataSet;
    }


    /**
     * gets K closests neighbours to the IPOINT p according to the choosenDistance (manhatthan/euclidian)
     *
     * @param p
     * @param k
     * @param choosenDistance
     * @return
     */
    public List<? extends IPoint> closeNeighbours(IPoint p, int k, IDistance choosenDistance) {
        if (k > baseDataSet.getNbLines()) k = baseDataSet.getNbLines() - 1;

        Map<IPoint, Double> distances = new HashMap<>();
        List<? extends IPoint> listWithoutCurrentPoint = new ArrayList<IPoint>(baseDataSet.getMyPoints());
        listWithoutCurrentPoint.remove(p);
        for (IPoint i : listWithoutCurrentPoint) {
            distances.put(i, choosenDistance.calculateDistance(p, i, dsToClassify, baseDataSet));
        }
        return distances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * initialize a map full of ClassColors and init a counter as their value
     *
     * @return
     */
    private HashMap<GenClass, Integer> initializeClassMap() {
        HashMap<GenClass, Integer> colorMap = new HashMap<>();
        colorMap.put(GenClass.FIRSTCLASS, 0);
        colorMap.put(GenClass.SECONDCLASS, 0);
        colorMap.put(GenClass.THIRDCLASS, 0);
        colorMap.put(GenClass.NULL, 0);
        return colorMap;
    }

    /**
     * tells whether or not there is another classColor with the same occurence in the colorMap
     *
     * @param colorMap
     * @param result
     * @return
     */
    private boolean thereIsAnotherEqual(HashMap<GenClass, Integer> colorMap, GenClass result, int resultvalue, IPoint p) {
        for (GenClass cc : colorMap.keySet()) {
            if (!cc.equals(result) && colorMap.get(cc) == resultvalue) return true;
        }
        return false;
    }


    /**
     * classify the given point
     *
     * @param addedPoint
     * @param closeNeighbour
     */
    public boolean classify(IPoint addedPoint, List<? extends IPoint> closeNeighbour) {
        HashMap<GenClass, Integer> colorMap = initializeClassMap();
        for (IPoint i : closeNeighbour) {
            if (i.getPointGenClass() != GenClass.NULL) {
                GenClass cc = i.getPointGenClass();
                colorMap.put(cc, (colorMap.get(cc) + 1));
            }
        }
        GenClass result = Collections.max(colorMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        int resultValue = colorMap.get(result);

        // si on ne peut pas départager on prend le point le plus proche
        if (thereIsAnotherEqual(colorMap, result, resultValue, addedPoint))
            result = closeNeighbour.get(0).getPointGenClass();

        GenClass temp = addedPoint.getPointGenClass();
        addedPoint.setPointGenClass(result);
        addedPoint. setRealClassFromGenClass();

        this.dsToClassify.addLine(addedPoint); // on pourra réafficher le dataSet avec le nouveau point

        return temp == result;
    }

    /**
     * Calculates the robustness of the KNN classifier
     *
     * @param k
     * @param choosenDistance
     * @return
     */
    public double robustness(int k, IDistance choosenDistance) {
        int res = 0;
        for (IPoint i : dsToClassify) {
            List<? extends IPoint> closests = closeNeighbours(i, k, choosenDistance);
            if (classify(i, closests)) res++;
        }
        return res * 100 / dsToClassify.getNbLines();
    }


}
