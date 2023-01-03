package view;

import both.DataSet;
import dataInterfaces.IPoint;
import iris.IrisVariety;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.ScrollEvent;

public class CreateChart {
    private final BaseChart baseChart = new BaseChart(this);

    public MainView getMainView() {
        return mainView;
    }

    private final MainView mainView;
    private final HandleChart handleChart = new HandleChart(this);

    public CreateChart(MainView mainView) {
        this.mainView = mainView;
    }

    void createChartIris(DataSet ds, int val) {
        //Iris series
        baseChart.createBaseChartIris(ds, val);
        MainView.nbScatterChartSeries=3;
        if(val==1){
            XYChart.Series series4 = new XYChart.Series();
            XYChart.Series series5 = new XYChart.Series();
            XYChart.Series series6 = new XYChart.Series();
            MainView.nbScatterChartSeries=6;
            series4.setName("SetosaClassified");
            series5.setName("VersicolorClassified");
            series6.setName("VirginicaClassified");
            for (IPoint myPoint : Main.modelClassifier.getDataSet().getMyPoints()) {
                if (myPoint.getValue(Main.modelClassifier.getDataSet().getColumn("variety")).equals(IrisVariety.SETOSA)) {
                    series4.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                    myPoint
                            )
                    );
                }
                if (myPoint.getValue(Main.modelClassifier.getDataSet().getColumn("variety")).equals(IrisVariety.VERSICOLOR)) {
                    series5.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                    myPoint)
                    );
                }
                if (myPoint.getValue(Main.modelClassifier.getDataSet().getColumn("variety")).equals(IrisVariety.VIRGINICA)) {
                    series6.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                    myPoint
                            )
                    );
                }
            }
            mainView.getController().scatterChart.getData().addAll(series4, series5, series6);
            mainView.getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
            mainView.getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
            mainView.getController().setListenerClickSeries(series4, val);
            mainView.getController().setListenerClickSeries(series5, val);
            mainView.getController().setListenerClickSeries(series6, val);
        }
        if(val==2){
            XYChart.Series series4 = new XYChart.Series();
            XYChart.Series series5 = new XYChart.Series();
            XYChart.Series series6 = new XYChart.Series();
            MainView.nbScatterChartSeries=6;
            series4.setName("SetosaRobustness");
            series5.setName("VersicolorRobustness");
            series6.setName("VirginicaRobustness");
            for (IPoint myPoint : Main.modelRobustesse.getDataSet().getMyPoints()) {
                if (myPoint.getValue(Main.modelRobustesse.getDataSet().getColumn("variety")).equals(IrisVariety.SETOSA)) {
                    series4.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                    myPoint
                            )
                    );
                }
                if (myPoint.getValue(Main.modelRobustesse.getDataSet().getColumn("variety")).equals(IrisVariety.VERSICOLOR)) {
                    series5.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                    myPoint)
                    );
                }
                if (myPoint.getValue(Main.modelRobustesse.getDataSet().getColumn("variety")).equals(IrisVariety.VIRGINICA)) {
                    series6.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                    myPoint
                            )
                    );
                }
            }
            mainView.getController().scatterChart.getData().addAll(series4, series5, series6);
            mainView.getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
            mainView.getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
            mainView.getController().setListenerClickSeries(series4, val);
            mainView.getController().setListenerClickSeries(series5, val);
            mainView.getController().setListenerClickSeries(series6, val);
        }

    }

    void createBaseChartIris(DataSet ds, int val){
        baseChart.createBaseChartIris(ds, val);
    }

    void createBaseChartTitanic(DataSet ds, int val){
       baseChart.createBaseChartTitanic(ds, val);
    }

    void createChartTitanic(DataSet ds, int val) {

        createBaseChartTitanic(ds, val);
        MainView.nbScatterChartSeries=2;
        if(val==1){
            XYChart.Series series3 = new XYChart.Series();
            XYChart.Series series4 = new XYChart.Series();
            MainView.nbScatterChartSeries=4;
            series3.setName("SurvivedClassified");
            series4.setName("RIPClassified");
            for (IPoint myPoint : Main.modelClassifier.getDataSet().getMyPoints()) {
                if (myPoint.getValue(Main.modelClassifier.getDataSet().getColumn("survived")).equals(true)) {
                    series3.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(Main.modelClassifier.getDataSet().getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(Main.modelClassifier.getDataSet().getColumn(Main.modele.getAxeY().getName())),
                                    myPoint
                            )
                    );
                }
                if (myPoint.getValue(Main.modelClassifier.getDataSet().getColumn("survived")).equals(false)) {
                    series4.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(Main.modelClassifier.getDataSet().getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(Main.modelClassifier.getDataSet().getColumn(Main.modele.getAxeY().getName())),
                                    myPoint)
                    );
                }
            }
            mainView.getController().scatterChart.getData().addAll(series3, series4);
            mainView.getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
            mainView.getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
            mainView.setListenerClickSeries(series3, val);
            mainView.setListenerClickSeries(series4, val);
        }
        if(val==2){
            XYChart.Series series3 = new XYChart.Series();
            XYChart.Series series4 = new XYChart.Series();
            MainView.nbScatterChartSeries=4;
            series3.setName("SurvivedRobustness");
            series4.setName("RIPRobustness");
            for (IPoint myPoint : Main.modelRobustesse.getDataSet().getMyPoints()) {
                if (myPoint.getValue(Main.modelRobustesse.getDataSet().getColumn("survived")).equals(true)) {
                    series3.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(Main.modelRobustesse.getDataSet().getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(Main.modelRobustesse.getDataSet().getColumn(Main.modele.getAxeY().getName())),
                                    myPoint
                            )
                    );
                }
                if (myPoint.getValue(Main.modelRobustesse.getDataSet().getColumn("survived")).equals(false)) {
                    series4.getData().add(
                            new XYChart.Data<Double, Double>(
                                    myPoint.getNormalizedValue(Main.modelRobustesse.getDataSet().getColumn(Main.modele.getAxeX().getName())),
                                    myPoint.getNormalizedValue(Main.modelRobustesse.getDataSet().getColumn(Main.modele.getAxeY().getName())),
                                    myPoint)
                    );
                }
            }
            mainView.getController().scatterChart.getData().addAll(series3, series4);
            mainView.getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
            mainView.getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
            mainView.setListenerClickSeries(series3, val);
            mainView.setListenerClickSeries(series4, val);
        }
    }

    void mouseOverGlow(NumberAxis n){
        handleChart.mouseOverGlow(n);
    }

    void handleScrollChart(ScrollEvent event) {
        handleChart.handleScrollChart(event);
    }


}