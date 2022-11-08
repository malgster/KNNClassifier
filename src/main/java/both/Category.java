package both;

import dataInterfaces.ICategory;
import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import lombok.EqualsAndHashCode;

import java.util.Iterator;
import java.util.List;


@EqualsAndHashCode
public class Category implements ICategory {

    private String categoryTitle;
    protected List<IPoint> myCategoryPoints;
    protected IColumn NormalizedColumn;



    @Override
    public String getTitle() {
        return categoryTitle;
    }

    @Override
    public int getNbLines() {
        return myCategoryPoints.size();
    }

    @Override
    public void setLines(List<IPoint> lines) {
        this.myCategoryPoints = lines;
    }

    @Override
    public void addLine(IPoint element) {
        this.myCategoryPoints.add(element);
    }

    @Override
    public void addAllLine(List<IPoint> elements) {
        this.myCategoryPoints.addAll(elements);
    }



    @Override
    public Iterator<IPoint> iterator() {
        return this.myCategoryPoints.iterator();
    }
}
