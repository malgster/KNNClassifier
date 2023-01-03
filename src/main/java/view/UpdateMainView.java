package view;

import MVC.Subject;
import both.GenClass;
import dataInterfaces.IPoint;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;

import java.util.List;

public class UpdateMainView {
    private final MainView mainView;

    public UpdateMainView(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * override method object from the Observer interface
     * looks at the model to be updated
     */
    public void update(Subject subj) {
        mainView.getController().scatterChart.getData().clear();
        // initialise la listeView avec les points du dataset
        if (mainView.getController().slider.getValue() == 0) {
            List<IPoint> values = (List<IPoint>) Main.modele.ds().getMyPoints();
            mainView.getController().pointListeView.setItems(FXCollections.observableList(values));

            if (mainView.getController().titleLabel.getText().equals("Iris")) {
                mainView.getCreateChart().createChartIris(Main.modele.getBaseDataSet(), (int) mainView.getController().slider.getValue());
            } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
                mainView.getCreateChart().createChartTitanic(Main.modele.getBaseDataSet(), (int) mainView.getController().slider.getValue());
            }

        } else if (mainView.getController().slider.getValue() == 1) {
            List<IPoint> values = (List<IPoint>) Main.modelClassifier.getDataSet().getMyPoints();
            mainView.getController().pointListeView.setItems(FXCollections.observableList(values));
            boolean blueFound = false;
            int cpt = 0;
            while (blueFound) {
                if (Main.modelClassifier.getDataSet().getPoint(cpt).getPointClass() == GenClass.SECONDCLASS) {
                    blueFound = true;
                }
                cpt++;
            }

            if (Main.modelClassifier.getDataSet().getPoint(0).getPointClass() != GenClass.NULL || blueFound) {
                if (mainView.getController().titleLabel.getText().equals("Iris")) {
                    mainView.getCreateChart().createChartIris(mainView.getBaseDataSet(), (int) mainView.getController().slider.getValue());
                } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
                    mainView.getCreateChart().createChartTitanic(mainView.getBaseDataSet(), (int) mainView.getController().slider.getValue());
                }

            } else {
                mainView.getDataSetClassifier().createDataSetClassifier((int) mainView.getController().slider.getValue());
            }

        } else if (mainView.getController().slider.getValue() == 2) {
            List<IPoint> values = (List<IPoint>) Main.modelRobustesse.getDataSet().getMyPoints();
            mainView.getController().pointListeView.setItems(FXCollections.observableList(values));
            if (mainView.getController().titleLabel.getText().equals("Iris")) {
                mainView.getCreateChart().createChartIris(mainView.getBaseDataSet(), (int) mainView.getController().slider.getValue());
            } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
                mainView.getCreateChart().createChartTitanic(mainView.getBaseDataSet(), (int) mainView.getController().slider.getValue());
            }
            mainView.setRobustnessValue((Double) Main.modelRobustesse.getRobustnessValue());
            mainView.getController().progressBar.setProgress(mainView.getRobustnessValue() / 100);
        }


        mainView.getController().scatterChart.setTitle(Main.modele.ds().getTitle());
        mainView.getController().titleLabel.setText(Main.modele.getLblTitle());
        mainView.addMouseoverGlow(mainView.getController().abscisseLabel);
        mainView.addMouseoverGlow(mainView.getController().ordonneLabel);
        mainView.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Z) {
                mainView.getController().ordonneLabel.setAutoRanging(false);
                mainView.getController().ordonneLabel.setLowerBound(mainView.getController().ordonneLabel.getLowerBound() + 0.02);
                mainView.getController().ordonneLabel.setUpperBound(mainView.getController().ordonneLabel.getUpperBound() + 0.02);
            } else if (e.getCode() == KeyCode.S) {
                mainView.getController().ordonneLabel.setAutoRanging(false);
                mainView.getController().ordonneLabel.setLowerBound(mainView.getController().ordonneLabel.getLowerBound() - 0.02);
                mainView.getController().ordonneLabel.setUpperBound(mainView.getController().ordonneLabel.getUpperBound() - 0.02);
            } else if (e.getCode() == KeyCode.D) {
                mainView.getController().abscisseLabel.setAutoRanging(false);
                mainView.getController().abscisseLabel.setLowerBound(mainView.getController().abscisseLabel.getLowerBound() + 0.02);
                mainView.getController().abscisseLabel.setUpperBound(mainView.getController().abscisseLabel.getUpperBound() + 0.02);
            } else if (e.getCode() == KeyCode.Q) {
                mainView.getController().abscisseLabel.setAutoRanging(false);
                mainView.getController().abscisseLabel.setLowerBound(mainView.getController().abscisseLabel.getLowerBound() - 0.02);
                mainView.getController().abscisseLabel.setUpperBound(mainView.getController().abscisseLabel.getUpperBound() - 0.02);
            } else if (e.getCode() == KeyCode.SHIFT) {
                mainView.getController().abscisseLabel.setAutoRanging(true);
                mainView.getController().abscisseLabel.setLowerBound(0);
                mainView.getController().abscisseLabel.setUpperBound(1);
                mainView.getController().ordonneLabel.setAutoRanging(true);
                mainView.getController().ordonneLabel.setLowerBound(0);
                mainView.getController().ordonneLabel.setUpperBound(1);
            }
        });
    }
}