package view;

import dataInterfaces.IColumn;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AttributeYView extends Application {

    AttributeYView controller;
    @FXML
    private ChoiceBox choiceY;


    /**
     * form to choose the y attribute in the scatterChart
     * @param mainStage the primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        //Chargement du fichier fxml de l'interface principale
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ihm/interfaces/Select_categoryY.fxml"));
        root = loader.load();
        this.controller = loader.getController();

        //initialisation de la Scene
        Scene scene = new Scene(root);

        //paramétrage du stage
        mainStage.setTitle("Choisir l'attribut de l'axe des ordonnées");
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        List<IColumn> values = Main.modele.ds().getColumns();
        List<String> noms = new ArrayList<>();
        for (IColumn c : values) {
            if(c.isNormalizable()) {
                noms.add(c.getName());
            }
        }
        controller.choiceY.setItems(FXCollections.observableList(noms));

        //affichage de l'interface
        mainStage.show();
    }

    /**
     * called when the button ok is pressed
     */
    public void handleOk(ActionEvent actionEvent) {
        Main.modele.setAxeY(Main.modele.ds().getColumn((String) choiceY.getSelectionModel().getSelectedItem()));
        Main.modelClassifier.setAxeY(Main.modele.ds().getColumn((String) choiceY.getSelectionModel().getSelectedItem()));
        Main.modelRobustesse.setAxeY(Main.modele.ds().getColumn((String) choiceY.getSelectionModel().getSelectedItem()));
        Stage stage = (Stage) choiceY.getScene().getWindow();
        stage.close();
    }

}



