package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * displays two radio buttons to choose from:  Iris or Titanic
 */
public class ImporterView extends Application {

    @FXML
    private RadioButton RadioButtonIris = new RadioButton();
    @FXML
    private RadioButton RadioButtonTitanic = new RadioButton();
    @FXML
    private Button ButtonSubmit = new Button();

    public ImporterView() {
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        //Chargement du fichier fxml de l'interface principale
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ihm/interfaces/Select_classifier.fxml"));

        //initialisation de la Scene
        Scene scene = new Scene(root);

        //paramétrage du stage
        mainStage.setTitle("Choisir le dataset à importer");
        mainStage.setResizable(false);
        mainStage.setScene(scene);

        //affichage de l'interface
        mainStage.show();

    }


    /**
     * used when the submit button is pressed
     */
    public void handleSubmit(MouseEvent mouseEvent) throws Exception {

        if (RadioButtonIris.isSelected()) {
            Main.modele.setLblTitle("Iris");
            Stage stage = (Stage) ButtonSubmit.getScene().getWindow();
            stage.close();
        } else {
            Main.modele.setLblTitle("Titanic");
            Stage stage = (Stage) ButtonSubmit.getScene().getWindow();
            stage.close();
        }

    }
}



