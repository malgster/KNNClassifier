package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChooseClassParamView extends Application {

    ChooseClassParamView controller;

    @FXML private ChoiceBox<String> chooseDistance;

    @FXML private TextField kValues;

    @Override
    public void start(Stage mainStage) throws Exception {
        //Chargement du fichier fxml de l'interface principale
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ihm/interfaces/Choose_classify_parameters.fxml"));
        root = loader.load();
        this.controller = loader.getController();

        //initialisation de la Scene
        Scene scene = new Scene(root);

        //paramétrage du stage
        mainStage.setTitle("Choisir les parametres");
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        controller.chooseDistance.getItems().add("Manhathan");
        controller.chooseDistance.getItems().add("Euclidian");

        //affichage de l'interface
        mainStage.show();
    }

    public void handleOk(ActionEvent actionEvent) {
        if(MainView.sliderValueInt==1) {
            Main.modelClassifier.classifyModel(chooseDistance.getSelectionModel().getSelectedItem().toString(), kValues.getText());
        }else if(MainView.sliderValueInt==2){
            Main.modelRobustesse.robustnessModel(chooseDistance.getSelectionModel().getSelectedItem().toString(), kValues.getText());
        }
        Stage stage = (Stage) kValues.getScene().getWindow();
        stage.close();
    }
}
