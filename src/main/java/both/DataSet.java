package both;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;

import java.util.*;

/**
 * represents the dataset with all its points
 */
public class DataSet implements IDataSet {

    /**
     * list of the dataset points
     */
    private List<? extends IPoint> myPoints;

    /**
     * dataset's name
     */
    private final String name;
    protected final Class<? extends IPoint> pointClass;

    /**
     * list of all columns
     */
    private List<IColumn> columnsGeneral = new ArrayList<>();

    /**
     * list of all columns without the class attribute
     */
    private List<IColumn> columnsWithoutClass = new ArrayList<>();

    public DataSet(final String name, Class<? extends IPoint> pointClass) {
        this.myPoints = new LinkedList<>();
        this.name = name;
        this.pointClass = pointClass;
        this.setAllColors();
    }

    /**
     * sets the colors (used in the robustness method) of the points
     */
    @Override
    public void setAllColors() {
        for (IPoint i : this.myPoints) {
            i.setColor();
        }
    }

    /**
     * sets the class column and initializes the columnsWithoutClass list
     * @param classe
     */
    public void setColumnClass(IColumn classe) {
        List<IColumn> withoutClass = new ArrayList<>();
        for(IColumn c : columnsGeneral){
            withoutClass.add(c);
        }
        withoutClass.remove(classe);
        withoutClass.remove(getColumn("color"));
        columnsWithoutClass.addAll(withoutClass);
    }


    public List<IColumn> getColumnsWithoutClass() {
        return columnsWithoutClass;
    }

    public List<IColumn> getColumns() {
        return columnsGeneral;
    }

    /**
     * adds the columns to the dataset
     * @param columns
     */
    public void setColumns(IColumn... columns) {
        LinkedList<IColumn> result = new LinkedList<>();
        result.addAll(List.of(columns));
        columnsGeneral= result;
    }

    /**
     * gets a column using its name
     * @param name
     * @return column if it is found, null if not
     */
    public IColumn getColumn(String name) {
        for (IColumn ic : columnsGeneral) {
            if (ic.getName().equals(name)) {
                return ic;
            }
        }
        return null;
    }

    /**
     * gets the column's minValue
     * @param col
     * @return minValue
     */
    public Number minValue(IColumn col) {
        Number min = (Number) myPoints.get(0).getValue(col);
        Number temp;
        for (IPoint i : this) {
            temp = (Number) i.getValue(col);
            if (temp.doubleValue() < min.doubleValue()) min = temp;
        }
        return min;
    }

    /**
     * gets the column's maxValue
     * @param col
     * @return maxValue
     */
    public Number maxValue(IColumn col) {
        Number max = (Number) myPoints.get(0).getValue(col);
        Number temp;
        for (IPoint i : this) {
            temp = (Number) i.getValue(col);
            if (temp.doubleValue() > max.doubleValue()) max = temp;
        }
        return max;
    }

    public IPoint getPoint(int index) {
        return myPoints.get(index);
    }

    /**
     * returns the column value of the point (from its index)
     * @param index
     * @param column
     * @return
     */
    public Object getValue(int index, IColumn column) {
        return getPoint(index).getValue(column);
    }

    @Override
    public String getTitle() {
        return this.name;
    }

    /**
     * @return number of points in the dataset
     */
    @Override
    public int getNbLines() {
        return this.myPoints.size();
    }

    /**
     * set the points in the dataset (from a list or the csv)
     * @param lines
     */
    @Override
    public void setLines(List<IPoint> lines) {
        this.myPoints = lines;
    }

    /**
     * add a point to the dataset
     * @param element
     */
    public void addLine(IPoint element) {
        // yes it's ugly but believe me it's the only way
        // to trick the compiler to accept my adding of Ipoint to a list of <? extends Ipoint>
        List<IPoint> temp = (List<IPoint>) this.myPoints;
        if (!temp.contains(element)) {
            temp.add(element);
            this.myPoints = temp;
        }
    }

    /**
     * add new points to the dataset from a list or a csv
     * @param element
     */
    @Override
    public void addAllLine(List<IPoint> element) {
        for (IPoint i : element) {
            this.addLine(i);
        }
    }

    /**
     * dataset is iterable on its points
     */
    @Override
    public Iterator<IPoint> iterator() {
        return (Iterator<IPoint>) this.myPoints.iterator();
    }

    public List<? extends IPoint> getMyPoints() {
        return myPoints;
    }


    public void setMyPoints(List<? extends IPoint> myPoints) {
        this.myPoints = myPoints;
    }

    public IColumn getColumn(int index) {
        return columnsGeneral.get(index);
    }

}

