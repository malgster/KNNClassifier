package view;

import both.DataSet;
import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import iris.IrisRawData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import titanic.TitanicRawData;

import java.util.ArrayList;
import java.util.List;

public class StartMainView {
    private final MainView mainView;

    public StartMainView(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * displays the page
     *
     * @param mainStage the primary stage for this application, onto which
     *                  the application scene can be set.
     */

    public void start(Stage mainStage) throws Exception {
        //Chargement du fichier fxml de l'interface principale
        Parent root;
        FXMLLoader loader = new FXMLLoader(mainView.getClass().getClassLoader().getResource("ihm/interfaces/Main.fxml"));
        root = loader.load();
        mainView.setController(loader.getController());
        //initialisation de la Scene
        mainView.setScene(new Scene(root));
        mainView.getScene().getStylesheets().add(mainView.getClass().getClassLoader().getResource("ihm/styles/dataset.css").toExternalForm());
        //paramétrage du stage
        mainStage.setTitle("Classifier");
        mainStage.setResizable(false);
        mainStage.setScene(mainView.getScene());
        mainView.getController().progressBar.setVisible(false);
        Main.modele.attach(mainView);
        Main.modelClassifier.attach(mainView);
        Main.modelRobustesse.attach(mainView);
        mainView.getController().slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                mainView.handleSlider(mainView.getController().slider.getValue(), mainView.getScene());
            }
        });
        mainView.getController().importerButton.setOnAction(e -> {
            try {
                mainView.getSliderValue().sliderValues();
                if (mainView.getController().slider.getValue() == 0) {
                    List<IColumn> colonnes = new ArrayList<IColumn>();
                    if (mainView.getController().titleLabel.getText().equals("Iris")) {
                        mainView.setBaseDataSet(new DataSet("IrisClassifier", IrisRawData.class));
                    } else if (mainView.getController().titleLabel.getText().equals("Titanic")) {
                        mainView.setBaseDataSet(new DataSet("TitanicClassifier", TitanicRawData.class));
                    }
                    for (IColumn i : Main.modele.getBaseDataSet().getColumns()) {
                        colonnes.add(i);
                    }
                    mainView.getBaseDataSet().setColumns(colonnes);
                    for (IPoint i : Main.modele.getBaseDataSet().getMyPoints()) {
                        mainView.getBaseDataSet().addLine(i);
                    }

                }
            } catch (Exception exception) {
                String errorMessage = "tu as fait de la merde billy";
                Dialog d = new Alert(Alert.AlertType.ERROR, errorMessage);
                d.show();
                exception.printStackTrace();
            }
        });
        //affichage de l'interface
        mainStage.show();
    }
}