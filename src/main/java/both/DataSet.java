package both;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * for polymorphism purposes, iris and TitanicPassenger inherits from it
 */
public class DataSet implements IDataSet {

   private List<IPoint> myPoints;

   private final String name;
   private final Class<? extends IPoint> pointClass;
   private final List<IColumn> columns;

   public DataSet(final String name, Class<? extends IPoint> pointClass, IColumn ...columns){
      this.name = name;
      this.pointClass = pointClass;
      this.columns = initColumns(columns);
   }

   public List<IColumn> initColumns(IColumn ...columns){
      LinkedList<IColumn> result = new LinkedList<>();
      for ( int i = 0; i < columns.length; result.add(columns[i]), i++);
      return result;
   }


   // TODO
   public double minValue(IColumn col) {return 0.0}
   public double maxValue(IColumn col){return 0;}

   @Override
   public String getTitle() {
      return this.name;
   }

   @Override
   public int getNbLines() {return this.myPoints.size();}

   @Override
   public void setLines(List<IPoint> lines) {
      this.myPoints = lines;
   }

   @Override
   public void addLine(IPoint element) {
      this.myPoints.add(pointClass.cast(element));
   }


   @Override
   public void addAllLine(List<IPoint> element) {
      for (IPoint i : element) {
         this.addLine(i);
      }
   }



   @Override
   public Iterator<IPoint> iterator() {
      return this.myPoints.iterator();
   }
}
