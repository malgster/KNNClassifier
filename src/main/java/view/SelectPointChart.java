package view;

import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SelectPointChart {
    private final MainView mainView;
    public SelectPointChart(MainView mainView) {
        this.mainView = mainView;
    }

    void selectPointChart(XYChart.Series series1, int val) {
        if(val==0) {
            forBaseModel(series1);
        }else if(val==1){
            forClassifierModel(series1);
        }else if(val==2){
            forRobustesseModel(series1);
        }
    }

    private void forBaseModel(XYChart.Series series1) {
        List<String> listeAAfficher = new ArrayList<String>();
        for (int i = 0; i < series1.getData().size(); i++) {
            XYChart.Data items = (XYChart.Data) series1.getData().get(i);
            mainView.selectPointChart.mouseOverGlow(items);
            items.getNode().setOnMouseClicked(e -> {
                if(mainView.getController()==null) {
                    mainView.listeSelected.getItems().clear();
                }else {
                    mainView.getController().listeSelected.getItems().clear();
                }
                IPoint point = (IPoint) items.getExtraValue();
                for (IColumn c : Main.modele.ds().getColumns()) {
                    listeAAfficher.add(c.getName() + ": " + point.getValue(c));
                }
                Main.modele.setSelectedPoint(point);
                if(mainView.getController()==null){
                    mainView.listeSelected.setItems(FXCollections.observableList(listeAAfficher));
                    mainView.selectedPointLabel.setText("Point Sélectionné");
                }else {
                    mainView.getController().listeSelected.setItems(FXCollections.observableList(listeAAfficher));
                    mainView.getController().selectedPointLabel.setText("Point Sélectionné");
                }
            });
        }
    }

    private void forClassifierModel(XYChart.Series series1) {
        List<String> listeAAfficher = new ArrayList<String>();
        for (int i = 0; i < series1.getData().size(); i++) {
            XYChart.Data items = (XYChart.Data) series1.getData().get(i);
            mainView.selectPointChart.mouseOverGlow(items);
            items.getNode().setOnMouseClicked(e -> {
                if(mainView.getController()==null) {
                    mainView.listeSelected.getItems().clear();
                }else {
                    mainView.getController().listeSelected.getItems().clear();
                }
                IPoint point = (IPoint) items.getExtraValue();
                for (IColumn c : Main.modelClassifier.getDataSet().getColumns()) {
                    listeAAfficher.add(c.getName() + ": " + point.getValue(c));
                }
                Main.modelClassifier.setSelectedPoint(point);
                if(mainView.getController()==null){
                    mainView.listeSelected.setItems(FXCollections.observableList(listeAAfficher));
                    mainView.selectedPointLabel.setText("Point Sélectionné");
                }else {
                    mainView.getController().listeSelected.setItems(FXCollections.observableList(listeAAfficher));
                    mainView.getController().selectedPointLabel.setText("Point Sélectionné");
                }
            });
        }
    }

    private void forRobustesseModel(XYChart.Series series1) {
        List<String> listeAAfficher = new ArrayList<String>();
        for (int i = 0; i < series1.getData().size(); i++) {
            XYChart.Data items = (XYChart.Data) series1.getData().get(i);
            mainView.selectPointChart.mouseOverGlow(items);
            items.getNode().setOnMouseClicked(e -> {
                if(mainView.getController()==null) {
                    mainView.listeSelected.getItems().clear();
                }else {
                    mainView.getController().listeSelected.getItems().clear();
                }
                IPoint point = (IPoint) items.getExtraValue();
                for (IColumn c : Main.modelRobustesse.getDataSet().getColumns()) {
                    listeAAfficher.add(c.getName() + ": " + point.getValue(c));
                }
                Main.modelRobustesse.setSelectedPoint(point);
                if(mainView.getController()==null){
                    mainView.listeSelected.setItems(FXCollections.observableList(listeAAfficher));
                    mainView.selectedPointLabel.setText("Point Sélectionné");
                }else {
                    mainView.getController().listeSelected.setItems(FXCollections.observableList(listeAAfficher));
                    mainView.getController().selectedPointLabel.setText("Point Sélectionné");
                }
            });
        }
    }


    void selectPointList() {
        List<String> listeAAfficher = new ArrayList<>();
        IPoint point = mainView.pointListeView.getSelectionModel().getSelectedItems().get(0);
        for (IColumn c : Main.modele.ds().getColumns()) {
            listeAAfficher.add(c.getName() + ": " + point.getValue(c));
        }
        Main.modele.setSelectedPoint(point);


        mainView.selectedPointLabel.setText("Point " + (mainView.pointListeView.getSelectionModel().getSelectedIndex() + 1));
        mainView.listeSelected.setItems(FXCollections.observableList(listeAAfficher));
        XYChart.Series series0 = new XYChart.Series<>();
        series0.setName("Selected Point");
        series0.getData().add(new XYChart.Data<>(
                point.getNormalizedValue(Main.modele.ds().getColumn(Main.modele.getAxeX().getName())),
                point.getNormalizedValue(Main.modele.ds().getColumn(Main.modele.getAxeY().getName()))
        ));

        if (mainView.scatterChart.getData().size() == mainView.nbScatterChartSeries) {
            mainView.scatterChart.getData().add(series0);
        } else {
            mainView.scatterChart.getData().set(mainView.nbScatterChartSeries, series0);
        }
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