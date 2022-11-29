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

public class AttributeXView extends Application {

    AttributeXView controller;
    @FXML
    private ChoiceBox choiceX;

    /**
     * form to choose the x attribute in the scatterChart
     * @param mainStage the primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        //Chargement du fichier fxml de l'interface principale
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ihm/interfaces/Select_categoryX.fxml"));
        root = loader.load();
        controller = loader.getController();
        //initialisation de la Scene
        Scene scene = new Scene(root);

        //paramétrage du stage
        mainStage.setTitle("Choisir l'attribut de l'axe des abcisses");
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        List<IColumn> values = Main.modele.getBaseDataSet().getColumns();
        List<String> noms = new ArrayList<>();
        for (IColumn c : values) {
            if(c.isNormalizable()) {
                noms.add(c.getName());
            }
        }
        controller.choiceX.setItems(FXCollections.observableList(noms));

        //affichage de l'interface
        mainStage.show();
    }

    /**
     * called when the button ok is pressed
     */
    public void handleOk(ActionEvent actionEvent) {
        Main.modele.setAxeX(Main.modele.getBaseDataSet().getColumn(choiceX.getSelectionModel().getSelectedItem().toString()));
        Stage stage = (Stage) choiceX.getScene().getWindow();
        stage.close();
    }

}



