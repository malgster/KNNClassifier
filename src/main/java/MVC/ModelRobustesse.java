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

public class ModelRobustesse extends Subject {
    String lblTitle;
    DataSet dataSetRobustesse;
    Classifier classifierRobustesse;
    IColumn axeX;
    IColumn axeY;
    List<String> formulaireValeurs;
    private IPoint selectedPoint;

    private DataSet baseDataSet;

    public double getRobustnessValue() {
        return robustnessValue;
    }

    private double robustnessValue;


    public void setBaseDataSetIris(DataSet ds) {
        this.baseDataSet=new DataSet("IrisSet", IrisRawData.class);
        List<IColumn> colonnes = new ArrayList<>();
        colonnes.addAll(ds.getColumns());
        this.baseDataSet.setColumns(colonnes);
        baseDataSet.setColumnClass(ds.getColumn(3));
        for(IPoint i : ds) {
            this.baseDataSet.addLine(i);
        }
    }

    public void setBaseDataSetTitanic(DataSet ds) {
        this.baseDataSet=new DataSet("TitanicSet", TitanicRawData.class);
        List<IColumn> colonnes = new ArrayList<>();
        colonnes.addAll(ds.getColumns());
        this.baseDataSet.setColumns(colonnes);
        baseDataSet.setColumnClass(ds.getColumn(1));
        for(IPoint i : ds) {
            this.baseDataSet.addLine(i);
        }
    }

    public void setFormulaireValeurs(List<String> formulaireValeurs) {
        this.formulaireValeurs = formulaireValeurs;
        if (this.getDataSet().getTitle().equals("IrisSetRobustesse")) {
            IrisRawData newPoint = new IrisRawData(Double.parseDouble(formulaireValeurs.get(0)), Double.parseDouble(formulaireValeurs.get(1)), Double.parseDouble(formulaireValeurs.get(2)), Double.parseDouble(formulaireValeurs.get(3)), IrisVariety.valueOf(formulaireValeurs.get(4)));
            newPoint.setPointGenClass();
            this.dataSetRobustesse.addLine(newPoint);
            for (int i=0; i<baseDataSet.getColumnsWithoutClass().size(); i++) {
                IColumn c = dataSetRobustesse.getColumnsWithoutClass().get(i);
                c.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetRobustesse.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetRobustesse.maxValue(c).doubleValue())));
                IColumn c2 = baseDataSet.getColumnsWithoutClass().get(i);
                c2.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetRobustesse.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetRobustesse.maxValue(c).doubleValue())));
            }
        }
        if (MainView.title.equals("Titanic")) {
            TitanicRawData newPoint = new TitanicRawData(Integer.parseInt(formulaireValeurs.get(0)), false, Integer.parseInt(formulaireValeurs.get(1)), formulaireValeurs.get(2), Gender.valueOf(formulaireValeurs.get(9).toUpperCase()), Double.parseDouble(formulaireValeurs.get(3)), Integer.parseInt(formulaireValeurs.get(4)), Integer.parseInt(formulaireValeurs.get(5)), formulaireValeurs.get(6), formulaireValeurs.get(7), Double.parseDouble(formulaireValeurs.get(8)), Embarked.valueOf(formulaireValeurs.get(10).toUpperCase()));
            newPoint.setPointGenClass();
            getDataSet().addLine(newPoint);
            for (int i=0; i<baseDataSet.getColumnsWithoutClass().size(); i++) {
                IColumn c=dataSetRobustesse.getColumnsWithoutClass().get(i);
                if (c.getName().equals("embarked")){
                    c.setNormalizer(new EnumNormalizer(Embarked.class));
                } else if (c.getName().equals("gen")){
                    c.setNormalizer(new EnumNormalizer(Gender.class));
                }else if(c.getNormalizerType().equals("null")){
                    c.setNormalizer(new NullNormalizer());
                }
                else{
                    c.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetRobustesse.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetRobustesse.maxValue(c).doubleValue())));
                };
                IColumn c2=baseDataSet.getColumnsWithoutClass().get(i);
                if(c2.getNormalizer().stringValue().equals("number")){
                    c2.setNormalizer(new NumberNormalizer(Double.min(baseDataSet.minValue(c).doubleValue(), dataSetRobustesse.minValue(c).doubleValue()), Double.max(baseDataSet.maxValue(c).doubleValue(), dataSetRobustesse.maxValue(c).doubleValue())));
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
        return this.dataSetRobustesse;
    }

    public String getLblTitle() {
        return this.lblTitle;
    }

    public void setLblTitle(String lbl) {
        this.lblTitle = lbl;
        this.notifyObservers(lbl);
    }

    public void setDatasetIris(String pathOfFile) {
        dataSetRobustesse = new DataSet("IrisSetRobustesse", IrisRawData.class);
        dataSetRobustesse.setMyPoints(CsvLoader.load(pathOfFile, IrisRawData.class));

        //instanciation de tout les colonnes du dataSet
        Column sepalLength = new Column("sepallength", dataSetRobustesse);
        Column sepalWidth = new Column("sepalwidth", dataSetRobustesse);
        Column petalLength = new Column("petallength", dataSetRobustesse);
        Column petalWidth = new Column("petalwidth", dataSetRobustesse);
        Column variety = new Column("variety", dataSetRobustesse);
        Column color = new Column("color", dataSetRobustesse);

        //attribution des normalizer aux colonnes
        petalLength.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(petalLength).doubleValue(), dataSetRobustesse.maxValue(petalLength).doubleValue()));
        petalWidth.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(petalWidth).doubleValue(), dataSetRobustesse.maxValue(petalWidth).doubleValue()));
        sepalLength.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(sepalLength).doubleValue(), dataSetRobustesse.maxValue(sepalLength).doubleValue()));
        sepalWidth.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(sepalWidth).doubleValue(), dataSetRobustesse.maxValue(sepalWidth).doubleValue()));
        variety.setNormalizer(new NullNormalizer());
        color.setNormalizer(new NullNormalizer());
        Column[] colonnes = new Column[]{sepalLength, sepalWidth, petalLength, petalWidth, variety, color};

        dataSetRobustesse.setColumns(colonnes);
        dataSetRobustesse.setAllColors();
        dataSetRobustesse.setColumnClass(variety);

        axeX = sepalLength;
        axeY = petalLength;
        classifierRobustesse = new Classifier(dataSetRobustesse, baseDataSet);
        this.notifyObservers();
    }

    public void setDatasetTitanic(String pathOfFile) {
        dataSetRobustesse = new DataSet("TitanicSetRobustesse", TitanicRawData.class);
        dataSetRobustesse.setMyPoints(CsvLoader.load(pathOfFile, TitanicRawData.class));
        //instanciation de tout les colonnes du dataSet
        Column passengerId = new Column("passengerid", dataSetRobustesse);
        Column survived = new Column("survived", dataSetRobustesse);
        Column passengerClass = new Column("passengerclass", dataSetRobustesse);
        Column name = new Column("name", dataSetRobustesse);
        Column gen = new Column("gen", dataSetRobustesse, new EnumNormalizer(Gender.class));
        Column age = new Column("age", dataSetRobustesse);
        Column sibSp = new Column("sibSp", dataSetRobustesse);
        Column parch = new Column("parch", dataSetRobustesse);
        Column ticket = new Column("ticket", dataSetRobustesse);
        Column cabin = new Column("cabin", dataSetRobustesse);
        Column fare = new Column("fare", dataSetRobustesse);
        Column embarked = new Column("embarked", dataSetRobustesse, new EnumNormalizer(Embarked.class));
        Column color = new Column("color", dataSetRobustesse);

        //attribution des normalizeur aux colonnes
        passengerId.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(passengerId).doubleValue(), dataSetRobustesse.maxValue(passengerId).doubleValue()));
        passengerClass.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(passengerClass).doubleValue(), dataSetRobustesse.maxValue(passengerClass).doubleValue()));
        age.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(age).doubleValue(), dataSetRobustesse.maxValue(age).doubleValue()));
        sibSp.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(sibSp).doubleValue(), dataSetRobustesse.maxValue(sibSp).doubleValue()));
        fare.setNormalizer(new NumberNormalizer(dataSetRobustesse.minValue(fare).doubleValue(), dataSetRobustesse.maxValue(fare).doubleValue()));
        Column[] colonnes = new Column[]{passengerId, survived, passengerClass, name, gen, age, sibSp, parch, ticket, cabin, fare, embarked, color};

        dataSetRobustesse.setColumns(colonnes);
        dataSetRobustesse.setColumnClass(survived);
        dataSetRobustesse.setAllColors();

        axeX = age;
        axeY = passengerClass;
        classifierRobustesse = new Classifier(dataSetRobustesse, baseDataSet);
        this.notifyObservers();
    }

    public void robustnessModel(String selectedItem, String text) {

        if (selectedItem.equals("Manhathan")) {
            robustnessValue = classifierRobustesse.robustness(Integer.parseInt(text), new ManhatthanDistance());
        } else if (selectedItem.equals("Euclidian")) {
            robustnessValue = classifierRobustesse.robustness(Integer.parseInt(text), new EuclidianDistance());
            this.notifyObservers();
        }
        this.notifyObservers();
    }

    public void setAxeY(IColumn column) {
        axeY=column;
    }

    public void setAxeX(IColumn column) {
        axeX=column;
    }
}

