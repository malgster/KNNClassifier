package both;

import dataInterfaces.IColumn;
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
    private HashMap<ClassColor, Integer> initializeColorMap() {
        HashMap<ClassColor, Integer> colorMap = new HashMap<>();
        colorMap.put(ClassColor.RED, 0);
        colorMap.put(ClassColor.BLUE, 0);
        colorMap.put(ClassColor.GREEN, 0);
        colorMap.put(ClassColor.NULL, 0);
        return colorMap;
    }

    /**
     * tells whether or not there is another classColor with the same occurence in the colorMap
     *
     * @param colorMap
     * @param result
     * @return
     */
    private boolean thereIsAnotherEqual(HashMap<ClassColor, Integer> colorMap, ClassColor result, int resultvalue, IPoint p) {
        for (ClassColor cc : colorMap.keySet()) {
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
        HashMap<ClassColor, Integer> colorMap = initializeColorMap();
        for (IPoint i : closeNeighbour) {
            if (i.getColor() != ClassColor.NULL) {
                ClassColor cc = i.getColor();
                colorMap.put(cc, (colorMap.get(cc) + 1));
            }
        }
        ClassColor result = Collections.max(colorMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        int resultValue = colorMap.get(result);

        // si on ne peut pas départager on prend le point le plus proche
        if (thereIsAnotherEqual(colorMap, result, resultValue, addedPoint))
            result = closeNeighbour.get(0).getColor();

        ClassColor temp = addedPoint.getColor();
        addedPoint.setColor(result);
        addedPoint.setClassFromColor();

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

    /**
     * surcharge de la méthode robustesse, utilisé pour la classe test
     * @param k
     * @param choosenDistance
     * @param pointsATester
     * @return
     */
    public double robustness(int k, IDistance choosenDistance, List<? extends IPoint> pointsATester) {
        int res = 0;
        for (IPoint i : pointsATester) {
            List<? extends IPoint> closests = closeNeighbours(i, k, choosenDistance);
            if (classify(i, closests)) res++;
        }
        return res * 100 / pointsATester.size();
    }


}
