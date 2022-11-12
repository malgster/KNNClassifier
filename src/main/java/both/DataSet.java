package both;

import dataInterfaces.IColumn;
import dataInterfaces.IDataSet;
import dataInterfaces.IPoint;

import java.util.*;

public class DataSet implements IDataSet{




   private List<? extends IPoint> myPoints;

   private final String name;



   protected final Class<? extends IPoint> pointClass;
   private List<IColumn> columns;

   public DataSet(final String name, Class<? extends IPoint> pointClass){
      this.myPoints = new LinkedList<>();
      this.name = name;
      this.pointClass = pointClass;
   }

   public void setColumns(IColumn ...columns){
      LinkedList<IColumn> result = new LinkedList<>();
      for ( int i = 0; i < columns.length; result.add(columns[i]), i++);
      this.columns = result;
   }

   public IColumn getColumn(String name){
      for (IColumn ic : columns){
         if (ic.getName().equals(name)) return ic;
      }
      return null;
   }

   public Number minValue(IColumn col) {
	   Number min = (Number)myPoints.get(0).getValue(col);;
	   Number temp;
	   for (IPoint i : this) {
		   temp = (Number) i.getValue(col);
		   if(temp.doubleValue() < min.doubleValue()) min = temp;
	   }
      return min;
   }
   public Number maxValue(IColumn col){
      Number max = (Number)myPoints.get(0).getValue(col);;
      Number temp;
      for (IPoint i : this) {
         temp = (Number) i.getValue(col);
         if(temp.doubleValue() > max.doubleValue()) max = temp;
      }
      return max;
   }

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


   public void addLine(IPoint element) {
      // yes it's very ugly but believe me it's the only way
      // to trick the compiler to accept my adding of Ipoint to a list of <? extends Ipoint>
      List<IPoint> temp = (List<IPoint>) this.myPoints;
      temp.add(element);
      this.myPoints = (List<? extends IPoint>) temp;
   }



   @Override
   public void addAllLine(List<IPoint> element) {
      for (IPoint i : element) {
         this.addLine(i);
      }
   }

   @Override
   public Iterator<IPoint> iterator() {
      return (Iterator<IPoint>) this.myPoints.iterator();
   }

   public List<? extends IPoint> getMyPoints() {
      return myPoints;
   }

   public Class<? extends IPoint> getPointClass() {
      return pointClass;
   }

   public void setMyPoints(List<? extends IPoint> myPoints) {
      this.myPoints = myPoints;
   }

}
