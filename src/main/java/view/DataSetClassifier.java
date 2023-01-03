package view;

import dataInterfaces.IPoint;
import javafx.scene.chart.XYChart;

public class DataSetClassifier {
    private final MainView mainView;

    public DataSetClassifier(MainView mainView) {
        this.mainView = mainView;
    }

    public void createDataSetClassifier(int val) {
        XYChart.Series seriesUndefined = new XYChart.Series();
        seriesUndefined.setName("Not Classifier Points");
        if (mainView.getController().titleLabel.getText().equals("Iris")) {
            mainView.getCreateChart().createBaseChartIris(mainView.getBaseDataSet(), val);
        } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
            mainView.getCreateChart().createBaseChartTitanic(mainView.getBaseDataSet(), val);
        }
        for (IPoint myPoint : Main.modelClassifier.getDataSet().getMyPoints()) {
            seriesUndefined.getData().add(
                    new XYChart.Data(
                            myPoint.getNormalizedValue(Main.modelClassifier.getDataSet().getColumn(Main.modelClassifier.getAxeY().getName())),
                            myPoint.getNormalizedValue(Main.modelClassifier.getDataSet().getColumn(Main.modelClassifier.getAxeY().getName())),
                            myPoint
                    )
            );
        }
        mainView.getController().scatterChart.getData().add(seriesUndefined);
        mainView.getController().abscisseLabel.setLabel(Main.modele.getAxeX().getName());
        mainView.getController().ordonneLabel.setLabel(Main.modele.getAxeY().getName());
        mainView.setListenerClickSeries(seriesUndefined, val);
    }
}