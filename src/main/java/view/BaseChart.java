package view;

import both.DataSet;
import dataInterfaces.IPoint;
import iris.IrisVariety;
import javafx.scene.chart.XYChart;

public class BaseChart {
    private final CreateChart createChart;

    public BaseChart(CreateChart createChart) {
        this.createChart = createChart;
    }

    void createBaseChartIris(DataSet ds, int val) {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        series1.setName("Setosa");
        series2.setName("Versicolor");
        series3.setName("Virginica");
        for (IPoint myPoint : ds.getMyPoints()) {
            if (myPoint.getValue(ds.getColumn("variety")).equals(IrisVariety.SETOSA)) {
                series1.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
            if (myPoint.getValue(ds.getColumn("variety")).equals(IrisVariety.VERSICOLOR)) {
                series2.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                myPoint)
                );
            }
            if (myPoint.getValue(ds.getColumn("variety")).equals(IrisVariety.VIRGINICA)) {
                series3.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
        }
        createChart.getMainView().getController().scatterChart.getData().addAll(series1, series2, series3);
        createChart.getMainView().getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
        createChart.getMainView().getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
        createChart.getMainView().setListenerClickSeries(series1, val);
        createChart.getMainView().setListenerClickSeries(series2, val);
        createChart.getMainView().setListenerClickSeries(series3, val);
    }

    void createBaseChartTitanic(DataSet ds, int val){
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Survived");
        series2.setName("RIP");
        for (IPoint myPoint : ds.getMyPoints()) {
            if (myPoint.getValue(ds.getColumn("survived")).equals(true)) {
                series1.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
            if (myPoint.getValue(ds.getColumn("survived")).equals(false)) {
                series2.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(ds.getColumn(Main.modele.getAxeY().getName())),
                                myPoint)
                );
            }
        }
        createChart.getMainView().getController().scatterChart.getData().addAll(series1, series2);
        createChart.getMainView().getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
        createChart.getMainView().getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
        createChart.getMainView().setListenerClickSeries(series1, val);
        createChart.getMainView().setListenerClickSeries(series2, val);
    }
}