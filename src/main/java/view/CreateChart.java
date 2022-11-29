package view;

import dataInterfaces.IPoint;
import iris.IrisVariety;
import javafx.event.EventHandler;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CreateChart {
    private final MainView mainView;

    public CreateChart(MainView mainView) {
        this.mainView = mainView;
    }

    void createChartIris() {
        //Iris series
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        series1.setName("Setosa");
        series3.setName("Versicolor");
        series2.setName("Virginica");
        for (IPoint myPoint : Main.modele.getBaseDataSet().getMyPoints()) {
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("variety")).equals(IrisVariety.SETOSA)) {
                series1.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("variety")).equals(IrisVariety.VERSICOLOR)) {
                series3.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint)
                );
            }
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("variety")).equals(IrisVariety.VIRGINICA)) {
                series2.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
        }
        mainView.getController().scatterChart.getData().clear();
        mainView.getController().scatterChart.getData().addAll(series1, series3, series2);
        mainView.getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
        mainView.getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());

        mainView.setListenerClickSeries(series1);
        mainView.setListenerClickSeries(series2);
        mainView.setListenerClickSeries(series3);
    }

    void createChartIrisHorsFXML() {
        //Iris series
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        series1.setName("Setosa");
        series3.setName("Versicolor");
        series2.setName("Virginica");
        for (IPoint myPoint : Main.modele.getBaseDataSet().getMyPoints()) {
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("variety")).equals(IrisVariety.SETOSA)) {
                series1.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("variety")).equals(IrisVariety.VERSICOLOR)) {
                series3.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint)
                );
            }
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("variety")).equals(IrisVariety.VIRGINICA)) {
                series2.getData().add(
                        new XYChart.Data<Double, Double>(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
        }
        mainView.scatterChart.getData().clear();
        mainView.scatterChart.getData().addAll(series1, series3, series2);
        mainView.abscisseLabel.setLabel(Main.modele.getAxeX().getName());
        mainView.ordonneLabel.setLabel(Main.modele.getAxeY().getName());

        mainView.setListenerClickSeries(series1);
        mainView.setListenerClickSeries(series2);
        mainView.setListenerClickSeries(series3);
    }

    void createChartTitanic() {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series1.setName("Survived");
        series2.setName("RIP");
        for (IPoint myPoint : Main.modele.getBaseDataSet().getMyPoints()) {
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("survived")).equals(true)) {
                series1.getData().add(
                        new XYChart.Data(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("survived")).equals(false)) {
                series2.getData().add(
                        new XYChart.Data(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
        }
        mainView.getController().scatterChart.getData().clear();
        mainView.getController().scatterChart.getData().addAll(series1, series2);
        mainView.getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
        mainView.getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
        mainView.setListenerClickSeries(series1);
        mainView.setListenerClickSeries(series2);
    }
    void createChartTitanicHorsFXML() {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series1.setName("Survived");
        series2.setName("RIP");
        for (IPoint myPoint : Main.modele.getBaseDataSet().getMyPoints()) {
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("survived")).equals(true)) {
                series1.getData().add(
                        new XYChart.Data(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
            if (myPoint.getValue(Main.modele.getBaseDataSet().getColumn("survived")).equals(false)) {
                series2.getData().add(
                        new XYChart.Data(
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                                myPoint.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName())),
                                myPoint
                        )
                );
            }
        }
        mainView.scatterChart.getData().clear();
        mainView.scatterChart.getData().addAll(series1, series2);
        mainView.abscisseLabel.setLabel(Main.modele.getAxeX().getName());
        mainView.ordonneLabel.setLabel(Main.modele.getAxeY().getName());
        mainView.setListenerClickSeries(series1);
        mainView.setListenerClickSeries(series2);
    }

    void mouseOverGlow(NumberAxis n){
        final Effect glow = new DropShadow(10, Color.GOLDENROD);
        n.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                n.setEffect(glow);
            }
        });
        n.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                n.setEffect(null);
            }
        });
    }
    void mouseOverGlow(XYChart.Data n){
        final Effect glow = new DropShadow(15, Color.PURPLE);
        n.getNode().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                n.getNode().setEffect(glow);
            }
        });
        n.getNode().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                n.getNode().setEffect(null);
            }
        });
    }

}