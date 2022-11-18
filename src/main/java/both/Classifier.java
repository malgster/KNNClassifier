package both;

import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import dataInterfaces.IColumn;
import dataInterfaces.IDistance;
import dataInterfaces.IPoint;
import utils.Format;

public class Classifier {


		private DataSet dsToClassify;

		public Classifier(DataSet dsToClassify) {
			this.dsToClassify = dsToClassify;
		}


	// changes whether we use EuclidianDistance or ManhatthanDistance
	public List<IPoint> closeNeighbours(IPoint p, int k, IDistance choosenDistance) {
		Map<IPoint, Double> distances=new HashMap<IPoint, Double>();
		for(IPoint i:dsToClassify.getMyPoints()) {
			distances.put(i, choosenDistance.calculateDistance(p, i, dsToClassify));
		}
		return  distances.entrySet().stream()
				.sorted(Map.Entry.<IPoint, Double>comparingByValue())
				.limit(3)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

	//Object classifier(IPoint p, int k, DataSet ds) ou HashMap<Object, Couleur> ? 

	public double robustesse(List<IPoint> liste, DataSet ds, int k) {
		/*
		int res=0;
		int nb=0;
		for(IPoint i:liste){
			if(classifier(i,k,ds).equals(i.getValue(ds.getClasse()))  res+=1; //regarde si la classe que classifier trouve est celle donnée dans le csv
			nb++;
		}
		return res/nb*100;  //return un pourcentage
		 */
		return 0.0;
	}
}
