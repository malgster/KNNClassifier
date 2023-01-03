package MVC;

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
import view.Main;
import view.MainView;

import java.util.List;

/**
 * Model for the MVC model
 */
public class Model extends Subject {

    String lblTitle;
    DataSet baseDataSet;
    Classifier defaultClassfier;
    IColumn axeX;
    IColumn axeY;
    List<String> formulaireValeurs;
    private IPoint selectedPoint;

    public Model() {
    }

    public Classifier getDefaultClassfier() {
        return defaultClassfier;
    }

    public void setDefaultClassfier(Classifier defaultClassfier) {
        this.defaultClassfier = defaultClassfier;
    }


    public void setFormulaireValeurs(List<String> formulaireValeurs) {
        this.formulaireValeurs = formulaireValeurs;
        if (this.ds().getTitle().equals("IrisSet")) {
            IrisRawData newPoint = new IrisRawData(Double.parseDouble(formulaireValeurs.get(0)), Double.parseDouble(formulaireValeurs.get(1)), Double.parseDouble(formulaireValeurs.get(2)), Double.parseDouble(formulaireValeurs.get(3)), IrisVariety.valueOf(formulaireValeurs.get(4).toUpperCase()));
            newPoint.setPointGenClass();
            this.ds().addLine(newPoint);
            for (IColumn c : this.ds().getColumnsWithoutClass()) {
                c.setNormalizer(new NumberNormalizer(baseDataSet.minValue(c).doubleValue(), baseDataSet.maxValue(c).doubleValue()));
            }
        }
        if (this.ds().getTitle().equals("TitanicSet")) {
            TitanicRawData newPoint = new TitanicRawData(Integer.parseInt(formulaireValeurs.get(0)), Boolean.parseBoolean(formulaireValeurs.get(11)), Integer.parseInt(formulaireValeurs.get(1)), formulaireValeurs.get(2), Gender.valueOf(formulaireValeurs.get(9).toUpperCase()), Double.parseDouble(formulaireValeurs.get(3)), Integer.parseInt(formulaireValeurs.get(4)), Integer.parseInt(formulaireValeurs.get(5)), formulaireValeurs.get(6), formulaireValeurs.get(7), Double.parseDouble(formulaireValeurs.get(8)), Embarked.valueOf(formulaireValeurs.get(10).toUpperCase()));
            newPoint.setPointGenClass();
            baseDataSet.addLine(newPoint);
            for (IColumn c : this.ds().getColumnsWithoutClass()) {
                if (c.getName().equals("embarked")){
                    c.setNormalizer(new EnumNormalizer(Embarked.class));
                } else if (c.getName().equals("gen")){
                    c.setNormalizer(new EnumNormalizer(Gender.class));
                }else if(c.getNormalizerType().equals("null")){
                    c.setNormalizer(new NullNormalizer());
                }
                else{
                    c.setNormalizer(new NumberNormalizer(baseDataSet.minValue(c).doubleValue(), baseDataSet.maxValue(c).doubleValue()));
                };
            }
        }
        this.notifyObservers();
    }

    public IPoint getSelectedPoint() {
        return selectedPoint;
    }

    public void setSelectedPoint(IPoint selectedPoint) {
        this.selectedPoint = selectedPoint;
    }

    public IColumn getAxeX() {
        return axeX;
    }

    public void setAxeX(IColumn axeX) {
        this.axeX = axeX;
        this.notifyObservers();
    }

    public IColumn getAxeY() {
        return axeY;
    }

    public void setAxeY(IColumn axeY) {
        this.axeY = axeY;
        this.notifyObservers();
    }

    public DataSet ds() {
        return this.baseDataSet;
    }

    public void setBaseDataSet(DataSet baseDataSet) {
        this.baseDataSet = baseDataSet;
    }

    public String getLblTitle() {
        return this.lblTitle;
    }

    public void setLblTitle(String lbl) {
        this.lblTitle = lbl;
        this.notifyObservers(lbl);

    }

    public void setDatasetIris(String pathOfFile) {
        baseDataSet = new DataSet("IrisSet", IrisRawData.class);
        baseDataSet.setMyPoints(CsvLoader.load(pathOfFile, IrisRawData.class));

        //instanciation de tout les colonnes du dataSet
        Column sepalLength = new Column("sepallength", baseDataSet);
        Column sepalWidth = new Column("sepalwidth", baseDataSet);
        Column petalLength = new Column("petallength", baseDataSet);
        Column petalWidth = new Column("petalwidth", baseDataSet);
        Column variety = new Column("variety", baseDataSet);
        Column color = new Column("color", baseDataSet);

        //attribution des normalizer aux colonnes
        petalLength.setNormalizer(new NumberNormalizer(baseDataSet.minValue(petalLength).doubleValue(), baseDataSet.maxValue(petalLength).doubleValue()));
        petalWidth.setNormalizer(new NumberNormalizer(baseDataSet.minValue(petalWidth).doubleValue(), baseDataSet.maxValue(petalWidth).doubleValue()));
        sepalLength.setNormalizer(new NumberNormalizer(baseDataSet.minValue(sepalLength).doubleValue(), baseDataSet.maxValue(sepalLength).doubleValue()));
        sepalWidth.setNormalizer(new NumberNormalizer(baseDataSet.minValue(sepalWidth).doubleValue(), baseDataSet.maxValue(sepalWidth).doubleValue()));
        variety.setNormalizer(new NullNormalizer());
        color.setNormalizer(new NullNormalizer());
        Column[] colonnes = new Column[]{sepalLength, sepalWidth, petalLength, petalWidth, variety, color};


        baseDataSet.setColumns(colonnes);
        baseDataSet.setAllColors();
        baseDataSet.setAllColors();
        baseDataSet.setColumnClass(variety);

        axeX = sepalLength;
        axeY = petalLength;
        defaultClassfier = new Classifier(baseDataSet, baseDataSet);
        if(MainView.sliderValueInt==0) {

            Main.modelClassifier.setBaseDataSetIris(baseDataSet);
            Main.modelRobustesse.setBaseDataSetIris(baseDataSet);
        }
        this.notifyObservers();
    }

    public void setDatasetTitanic(String pathOfFile) {
        baseDataSet = new DataSet("TitanicSet", TitanicRawData.class);
        baseDataSet.setMyPoints(CsvLoader.load(pathOfFile, TitanicRawData.class));
        //instanciation de tout les colonnes du dataSet
        Column passengerId = new Column("passengerid", baseDataSet);
        Column survived = new Column("survived", baseDataSet);
        Column passengerClass = new Column("passengerclass", baseDataSet);
        Column name = new Column("name", baseDataSet);
        Column gen = new Column("gen", baseDataSet, new EnumNormalizer(Gender.class));
        Column age = new Column("age", baseDataSet);
        Column sibSp = new Column("sibSp", baseDataSet);
        Column parch = new Column("parch", baseDataSet);
        Column ticket = new Column("ticket", baseDataSet);
        Column cabin = new Column("cabin", baseDataSet);
        Column fare = new Column("fare", baseDataSet);
        Column embarked = new Column("embarked", baseDataSet, new EnumNormalizer(Embarked.class));
        Column color = new Column("color", baseDataSet);

        //attribution des normalizeur aux colonnes
        passengerId.setNormalizer(new NumberNormalizer(baseDataSet.minValue(passengerId).doubleValue(), baseDataSet.maxValue(passengerId).doubleValue()));
        passengerClass.setNormalizer(new NumberNormalizer(baseDataSet.minValue(passengerClass).doubleValue(), baseDataSet.maxValue(passengerClass).doubleValue()));
        age.setNormalizer(new NumberNormalizer(baseDataSet.minValue(age).doubleValue(), baseDataSet.maxValue(age).doubleValue()));
        sibSp.setNormalizer(new NumberNormalizer(baseDataSet.minValue(sibSp).doubleValue(), baseDataSet.maxValue(sibSp).doubleValue()));
        fare.setNormalizer(new NumberNormalizer(baseDataSet.minValue(fare).doubleValue(), baseDataSet.maxValue(fare).doubleValue()));
        Column[] colonnes = new Column[]{passengerId, survived, passengerClass, name, gen, age, sibSp, parch, ticket, cabin, fare, embarked, color};

        baseDataSet.setColumns(colonnes);
        baseDataSet.setColumnClass(survived);
        baseDataSet.setAllColors();

        axeX = age;
        axeY = passengerClass;
        defaultClassfier = new Classifier(baseDataSet, baseDataSet);
        Main.modelClassifier.setBaseDataSetTitanic(baseDataSet);
        Main.modelRobustesse.setBaseDataSetTitanic(baseDataSet);
        this.notifyObservers();
    }

    public DataSet getBaseDataSet() {
        return baseDataSet;
    }
}
