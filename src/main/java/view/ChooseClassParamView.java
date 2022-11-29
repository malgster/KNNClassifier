package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ChooseClassParamView extends Application {

    ChooseClassParamView controller;
    @FXML
    private VBox vBoxAttributes;
    @FXML
    private List<TextField> formulaires;

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

        //affichage de l'interface
        mainStage.show();
    }

    public void handleOk(ActionEvent actionEvent) {
        //handle du bouton ok

    }
}
