package view;

import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class SelectPointChart {
    private final MainView mainView;
    public SelectPointChart(MainView mainView) {
        this.mainView = mainView;
    }

    void selectPointChart(XYChart.Series series1) {
        List<String> listeAAfficher = new ArrayList<String>();
        for (int i = 0; i < series1.getData().size(); i++) {
            XYChart.Data items = (XYChart.Data) series1.getData().get(i);
            mainView.createChart.mouseOverGlow(items);
            items.getNode().setOnMouseClicked(e -> {

                mainView.getController().listeSelected.getItems().clear();
                IPoint point = (IPoint) items.getExtraValue();
                for (IColumn c : Main.modele.getBaseDataSet().getColumns()) {
                    listeAAfficher.add(c.getName() + ": " + point.getValue(c));
                }
                Main.modele.setSelectedPoint(point);
                System.out.println("voici le points que tu as choisis" + point);
                mainView.getController().listeSelected.setItems(FXCollections.observableList(listeAAfficher));
                mainView.getController().selectedPointLabel.setText("Point Sélectionné");
            });
        }
    }

    void selectPointList() {
        List<String> listeAAfficher = new ArrayList<>();
        IPoint point = mainView.pointListeView.getSelectionModel().getSelectedItems().get(0);
        for (IColumn c : Main.modele.getBaseDataSet().getColumns()) {
            listeAAfficher.add(c.getName() + ": " + point.getValue(c));
        }
        Main.modele.setSelectedPoint(point);


        mainView.selectedPointLabel.setText("Point " + (mainView.pointListeView.getSelectionModel().getSelectedIndex() + 1));
        mainView.listeSelected.setItems(FXCollections.observableList(listeAAfficher));
        XYChart.Series series4 = new XYChart.Series<>();
        series4.setName("Selected Point");
        series4.getData().add(new XYChart.Data<>(
                point.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeX().getName())),
                point.getNormalizedValue(Main.modele.getBaseDataSet().getColumn(Main.modele.getAxeY().getName()))
        ));

        if (mainView.scatterChart.getData().size() == mainView.nbScatterChartSeries) {
            mainView.scatterChart.getData().add(series4);
        } else {
            mainView.scatterChart.getData().set(mainView.nbScatterChartSeries, series4);
        }
    }
}