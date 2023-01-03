package MVC;

import Distances.EuclidianDistance;
import Distances.ManhatthanDistance;
import Normalizers.EnumNormalizer;
import Normalizers.NullNormalizer;
import Normalizers.NumberNormalizer;
import both.Classifier;
import both.Column;
import both.DataSet;
import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import iris.IrisRawData;
import iris.IrisVariety;
import titanic.Embarked;
import titanic.Gender;
import titanic.TitanicRawData;
import utils.CsvLoader;
import view.MainView;

import java.util.ArrayList;
import java.util.List;

public class ModelClassifier extends Subject {
    String lblTitle;
    DataSet dataSetClassifier;
    Classifier classifierClassifier;
    IColumn axeX;
    IColumn axeY;
    List<String> formulaireValeurs;
    private IPoint selectedPoint;

    private DataSet baseDataSet;

    public ModelClassifier() {
    }

    public void setBaseDataSetIris(DataSet ds) {
        this.baseDataSet=new DataSet("IrisSetClassifier", IrisRawData.class);
        List<IColumn> colonnes = new ArrayList<>();
        colonnes.addAll(ds.getColumns());
        this.baseDataSet.setColumns(colonnes);
        baseDataSet.setColumnClass(ds.getColumns().get(4));
        for(IPoint i : ds) {
            this.baseDataSet.addLine(i);
        }
    }

    public void setBaseDataSetTitanic(DataSet ds) {
        this.baseDataSet=new DataSet("TitanicSetClassifier", TitanicRawData.class);
        List<IColumn> colonnes = new ArrayList<>();
        colonnes.addAll(ds.getColumns());
        this.baseDataSet.setColumns(colonnes);
        baseDataSet.setColumnClass(ds.getColumns().get(1));
        for(IPoint i : ds) {
            this.baseDataSet.addLine(i);
        }
    }

    public void setFormulaireValeurs(List<String> formulaireValeurs) {
        this.formulaireValeurs = formulaireValeurs;
        if (MainView.title.equals("Iris")) {
            IrisRawData newPoint = new IrisRawData(Double.parseDouble(formulaireValeurs.get(0)), Double.parseDouble(formulaireValeurs.get(1)), Double.parseDouble(formulaireValeurs.get(2)), Double.parseDouble(formulaireValeurs.get(3)), null);
            newPoint.setPointGenClass();
            this.getDataSet().addLine(newPoint);
            for (int i=0; i<baseDataSet.getColumnsWithoutClass().size(); i++) {
                IColumn c = dataSetClassifier.getColumnsWithoutClass().get(i);
                c.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetClassifier.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetClassifier.maxValue(c).doubleValue())));
                IColumn c2 = baseDataSet.getColumnsWithoutClass().get(i);
                c2.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetClassifier.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetClassifier.maxValue(c).doubleValue())));
            }
        }
        if (MainView.title.equals("Titanic")) {
            TitanicRawData newPoint = new TitanicRawData(Integer.parseInt(formulaireValeurs.get(0)), false, Integer.parseInt(formulaireValeurs.get(1)), formulaireValeurs.get(2), Gender.valueOf(formulaireValeurs.get(9).toUpperCase()), Double.parseDouble(formulaireValeurs.get(3)), Integer.parseInt(formulaireValeurs.get(4)), Integer.parseInt(formulaireValeurs.get(5)), formulaireValeurs.get(6), formulaireValeurs.get(7), Double.parseDouble(formulaireValeurs.get(8)), Embarked.valueOf(formulaireValeurs.get(10).toUpperCase()));
            newPoint.setPointGenClass();
            getDataSet().addLine(newPoint);
            for (int i=0; i<baseDataSet.getColumnsWithoutClass().size(); i++) {
                IColumn c=dataSetClassifier.getColumnsWithoutClass().get(i);
                if (c.getName().equals("embarked")){
                    c.setNormalizer(new EnumNormalizer(Embarked.class));
                } else if (c.getName().equals("gen")){
                    c.setNormalizer(new EnumNormalizer(Gender.class));
                }else if(c.getNormalizerType().equals("null")){
                    c.setNormalizer(new NullNormalizer());
                }
                else{
                    c.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetClassifier.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetClassifier.maxValue(c).doubleValue())));
                };
                IColumn c2=baseDataSet.getColumnsWithoutClass().get(i);
                if (c2.getName().equals("embarked")){
                    c2.setNormalizer(new EnumNormalizer(Embarked.class));
                } else if (c2.getName().equals("gen")){
                    c2.setNormalizer(new EnumNormalizer(Gender.class));
                }else if(c2.getNormalizerType().equals("null")){
                    c2.setNormalizer(new NullNormalizer());
                }
                else{
                    c2.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetClassifier.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetClassifier.maxValue(c).doubleValue())));
                };
            }
        }
    }

    public IPoint getSelectedPoint() {
        return selectedPoint;
    }

    public void setSelectedPoint(IPoint selectedPoint) {
        this.selectedPoint = selectedPoint;
    }

    public IColumn getAxeY() {
        return axeY;
    }

    public DataSet getDataSet() {
        return this.dataSetClassifier;
    }

    public DataSet getBaseDataSet() {
        return this.baseDataSet;
    }

    public String getLblTitle() {
        return this.lblTitle;
    }

    public void setLblTitle(String lbl) {
        this.lblTitle = lbl;
        this.notifyObservers(lbl);
    }

    public void setDatasetIris(String pathOfFile) {

        dataSetClassifier = new DataSet("IrisSet", IrisRawData.class);
        dataSetClassifier.setMyPoints(CsvLoader.load(pathOfFile, IrisRawData.class));

        //instanciation de tout les colonnes du dataSet
        Column sepalLength = new Column("sepallength", dataSetClassifier);
        Column sepalWidth = new Column("sepalwidth", dataSetClassifier);
        Column petalLength = new Column("petallength", dataSetClassifier);
        Column petalWidth = new Column("petalwidth", dataSetClassifier);
        Column variety = new Column("variety", dataSetClassifier);
        Column color = new Column("color", dataSetClassifier);

        //attribution des normalizer aux colonnes
        petalLength.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(petalLength).doubleValue(), dataSetClassifier.maxValue(petalLength).doubleValue()));
        petalWidth.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(petalWidth).doubleValue(), dataSetClassifier.maxValue(petalWidth).doubleValue()));
        sepalLength.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(sepalLength).doubleValue(), dataSetClassifier.maxValue(sepalLength).doubleValue()));
        sepalWidth.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(sepalWidth).doubleValue(), dataSetClassifier.maxValue(sepalWidth).doubleValue()));
        variety.setNormalizer(new NullNormalizer());
        color.setNormalizer(new NullNormalizer());
        Column[] colonnes = new Column[]{sepalLength, sepalWidth, petalLength, petalWidth, variety, color};

        dataSetClassifier.setColumns(colonnes);
        dataSetClassifier.setAllColors();
        dataSetClassifier.setColumnClass(variety);

        axeX = sepalLength;
        axeY = petalLength;
        classifierClassifier = new Classifier(dataSetClassifier, baseDataSet);
        this.notifyObservers();
    }

    public void setDatasetTitanic(String pathOfFile) {
        dataSetClassifier = new DataSet("TitanicSet", TitanicRawData.class);
        dataSetClassifier.setMyPoints(CsvLoader.load(pathOfFile, TitanicRawData.class));
        //instanciation de tout les colonnes du dataSet
        Column passengerId = new Column("passengerid", dataSetClassifier);
        Column survived = new Column("survived", dataSetClassifier);
        Column passengerClass = new Column("passengerclass", dataSetClassifier);
        Column name = new Column("name", dataSetClassifier);
        Column gen = new Column("gen", dataSetClassifier, new EnumNormalizer(Gender.class));
        Column age = new Column("age", dataSetClassifier);
        Column sibSp = new Column("sibSp", dataSetClassifier);
        Column parch = new Column("parch", dataSetClassifier);
        Column ticket = new Column("ticket", dataSetClassifier);
        Column cabin = new Column("cabin", dataSetClassifier);
        Column fare = new Column("fare", dataSetClassifier);
        Column embarked = new Column("embarked", dataSetClassifier, new EnumNormalizer(Embarked.class));
        Column color = new Column("color", dataSetClassifier);

        //attribution des normalizeur aux colonnes
        passengerId.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(passengerId).doubleValue(), dataSetClassifier.maxValue(passengerId).doubleValue()));
        passengerClass.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(passengerClass).doubleValue(), dataSetClassifier.maxValue(passengerClass).doubleValue()));
        age.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(age).doubleValue(), dataSetClassifier.maxValue(age).doubleValue()));
        sibSp.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(sibSp).doubleValue(), dataSetClassifier.maxValue(sibSp).doubleValue()));
        fare.setNormalizer(new NumberNormalizer(dataSetClassifier.minValue(fare).doubleValue(), dataSetClassifier.maxValue(fare).doubleValue()));
        Column[] colonnes = new Column[]{passengerId, survived, passengerClass, name, gen, age, sibSp, parch, ticket, cabin, fare, embarked, color};

        dataSetClassifier.setColumns(colonnes);
        dataSetClassifier.setColumnClass(survived);
        dataSetClassifier.setAllColors();

        axeX = age;
        axeY = passengerClass;
        classifierClassifier = new Classifier(dataSetClassifier, baseDataSet);
        this.notifyObservers();
    }

    public void classifyModel(String selectedItem, String text) {

        if (selectedItem.equals("Manhathan")) {
            for (IPoint myPoint : dataSetClassifier.getMyPoints()) {
                classifierClassifier.classify(myPoint, classifierClassifier.closeNeighbours(myPoint, Integer.parseInt(text), new ManhatthanDistance()));
                myPoint. setRealClassFromGenClass();
            }
        } else if (selectedItem.equals("Euclidian")) {
            for (IPoint myPoint : dataSetClassifier.getMyPoints()) {
                classifierClassifier.classify(myPoint, classifierClassifier.closeNeighbours(myPoint, Integer.parseInt(text), new EuclidianDistance()));
                myPoint. setRealClassFromGenClass();
            }
        }
        this.notifyObservers();
    }

    public void notifyChanged() {
        this.notifyObservers();
    }

    public void setAxeX(IColumn column) {
        axeX = column;
    }

    public void setAxeY(IColumn column) {
        axeY=column;
    }
}

