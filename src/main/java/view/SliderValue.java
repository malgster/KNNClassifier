package view;

import javafx.scene.Scene;
import javafx.stage.FileChooser;

import java.io.File;

public class SliderValue {
    private final MainView mainView;

    public SliderValue(MainView mainView) {
        this.mainView = mainView;
    }

    void sliderValues() throws Exception {
        if (mainView.getController().slider.getValue() == 0) {
            MainView.sliderValueInt=0;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select your DataSet");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(null);
            // initialise le dataSet du model selon si c'est des Iris ou des Pasenger
            if (mainView.getController().titleLabel.getText().equals("Iris")) {
                //permet d'initialiser le dataSet du Model correspondant aux attributs des iris");
                Main.modele.setDatasetIris(file.getPath());
            } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
                //permet d'initialiser le dataSet du Model correspondant aux attributs des passengers
                Main.modele.setDatasetTitanic(file.getPath());
            }

        } else if (mainView.getController().slider.getValue() == 1) {
            Main.modele.setLblTitle(mainView.getController().titleLabel.getText());
            MainView.sliderValueInt=1;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select your Classifier");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(null);

            // initialise le dataSet du modelClassifier selon si c'est des Iris ou des Pasenger
            if (mainView.getController().titleLabel.getText().equals("Iris")) {
                //permet d'initialiser le dataSet du ModelClassifier correspondant aux attributs des iris");
                Main.modelClassifier.setDatasetIris(file.getPath());
            } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
                //permet d'initialiser le dataSet du ModelClassifier correspondant aux attributs des passengers
                Main.modelClassifier.setDatasetTitanic(file.getPath());
            }

        } else if (mainView.getController().slider.getValue() == 2) {
            Main.modele.setLblTitle(mainView.getController().titleLabel.getText());
            MainView.sliderValueInt=2;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select your Robustesse");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(null);
            if (mainView.getController().titleLabel.getText().equals("Iris")) {
                //permet d'initialiser le dataSet du ModelRobustesse correspondant aux attributs des iris");
                Main.modelRobustesse.setDatasetIris(file.getPath());
            } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
                //permet d'initialiser le dataSet du ModelRobustesse correspondant aux attributs des passengers
                Main.modelRobustesse.setDatasetTitanic(file.getPath());
            }
        }


    }
    void sliderCss(double number, Scene scene) throws Exception {
        Main.modele.setLblTitle(mainView.getController().titleLabel.getText());
        if (number <= 0.1) {
            MainView.sliderValueInt=0;
            scene.getStylesheets().remove(getClass().getClassLoader().getResource("ihm/styles/robustesse.css").toExternalForm());
            scene.getStylesheets().remove(getClass().getClassLoader().getResource("ihm/styles/classifier.css").toExternalForm());
            scene.getStylesheets().add(getClass().getClassLoader().getResource("ihm/styles/dataset.css").toExternalForm());
            mainView.controller.coverageButton.setText("");
            mainView.controller.coverageButton.setDisable(true);
            mainView.controller.pointListeView.getItems().clear();
            if(Main.modele.ds()!=null) {
                mainView.controller.pointListeView.getItems().addAll(Main.modele.ds().getMyPoints());
            }

        } else if (number > 0.1 && number < 1.9) {
            MainView.sliderValueInt=1;
            Main.modelClassifier.setLblTitle(mainView.getController().titleLabel.getText());
            scene.getStylesheets().remove(getClass().getClassLoader().getResource("ihm/styles/robustesse.css").toExternalForm());
            scene.getStylesheets().remove(getClass().getClassLoader().getResource("ihm/styles/dataset.css").toExternalForm());
            scene.getStylesheets().add(getClass().getClassLoader().getResource("ihm/styles/classifier.css").toExternalForm());
            mainView.controller.coverageButton.setText("Classify");
            mainView.controller.coverageButton.setDisable(false);
            mainView.controller.pointListeView.getItems().clear();
            if(Main.modelClassifier.getDataSet()!=null) {
                mainView.controller.pointListeView.getItems().addAll(Main.modelClassifier.getDataSet().getMyPoints());
            }


        } else if (number >= 1.9) {
            MainView.sliderValueInt=2;
            Main.modelRobustesse.setLblTitle(mainView.getController().titleLabel.getText());
            scene.getStylesheets().remove(getClass().getClassLoader().getResource("ihm/styles/dataset.css").toExternalForm());
            scene.getStylesheets().remove(getClass().getClassLoader().getResource("ihm/styles/classifier.css").toExternalForm());
            scene.getStylesheets().add(getClass().getClassLoader().getResource("ihm/styles/robustesse.css").toExternalForm());
            mainView.controller.coverageButton.setText("Coverage");
            mainView.controller.coverageButton.setDisable(false);
            mainView.controller.pointListeView.getItems().clear();
        }
    }

}