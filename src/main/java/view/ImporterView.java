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
    private RadioButton radioButtonIris = new RadioButton();
    @FXML
    private RadioButton radioButtonTitanic = new RadioButton();
    @FXML
    private Button buttonSubmit = new Button();

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

        if (radioButtonIris.isSelected()) {
            Main.modele.setLblTitle("Iris");
            MainView.title="Iris";
            Stage stage = (Stage) buttonSubmit.getScene().getWindow();
            stage.close();
        } else {
            Main.modele.setLblTitle("Titanic");
            MainView.title="Titanic";
            Stage stage = (Stage) buttonSubmit.getScene().getWindow();
            stage.close();
        }

    }
}



