package view;

import iris.IrisVariety;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import titanic.Embarked;
import titanic.Gender;

import java.util.ArrayList;
import java.util.List;

/**
 * the form to add a point
 */
public class AddPointView extends Application {

    AddPointView controller;
    @FXML
    private VBox vBoxAttributes;
    @FXML
    private List<TextField> formulaires = new ArrayList<>();

    @FXML
    private ChoiceBox classeValue = new ChoiceBox();

    @FXML
    private ChoiceBox embarkedValue = new ChoiceBox();

    @FXML
    private ChoiceBox genValue = new ChoiceBox<>();

    /**
     * displays the form
     * @param mainStage the primary stage for this application, onto which
     * the application scene can be set.
     * @throws Exception
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        //Chargement du fichier fxml de l'interface principale
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ihm/interfaces/Add_point.fxml"));
        root = loader.load();
        //initialisation de la Scene
        Scene scene = new Scene(root);

        //paramétrage du stage
        mainStage.setTitle("Ajouter un Point");
        mainStage.setResizable(false);
        mainStage.setScene(scene);


        initialize(loader);
        //affichage de l'interface
        mainStage.show();
    }

    /**
     * initializes the form with textfields for numeric and text values and choiceBoxes for enum and boolean values
     */
    public void initialize(FXMLLoader loader){
        this.controller = loader.getController();
        HBox boiteformulaire;
        TextField formulaire;
        //affichage des formulaires (ui c'est long)
        for (int i = 0; i < Main.modele.getBaseDataSet().getColumnsWithoutClass().size(); i++) {
            if (!Main.modele.getBaseDataSet().getColumnsWithoutClass().get(i).getName().equals("embarked") && !Main.modele.getBaseDataSet().getColumnsWithoutClass().get(i).getName().equals("gen")) {
                boiteformulaire = new HBox();
                formulaire = new TextField();
                formulaire.setPromptText(Main.modele.getBaseDataSet().getColumnsWithoutClass().get(i).getName());
                controller.formulaires.add(formulaire);
                boiteformulaire.getChildren().add(formulaire);
                boiteformulaire.setAlignment(Pos.CENTER);
                controller.vBoxAttributes.getChildren().add(boiteformulaire);
            }
        }

        if (Main.modele.getBaseDataSet().getTitle().equals("IrisSet")) {
            List<String> varietiesValues = new ArrayList<>();
            for (IrisVariety var : IrisVariety.values()) {
                varietiesValues.add(var.toString());
            }
            controller.classeValue.setItems(FXCollections.observableList(varietiesValues));
            controller.classeValue.setValue(varietiesValues.get(0));
            HBox choice = new HBox();
            choice.setAlignment(Pos.CENTER);
            choice.getChildren().add(new Label("variety"));
            choice.getChildren().add(this.controller.classeValue);
            controller.vBoxAttributes.getChildren().add(choice);

        } else if (Main.modele.getBaseDataSet().getTitle().equals("TitanicSet")) {
            List<String> booleanValues = new ArrayList<>();
            booleanValues.add("true");
            booleanValues.add("false");

            List<String> embarkedValues = new ArrayList<>();
            for (Embarked var : Embarked.values()) {
                embarkedValues.add(var.toString());
            }

            List<String> genValues = new ArrayList<>();
            for (Gender var : Gender.values()) {
                genValues.add(var.toString());
            }

            controller.embarkedValue.setValue(embarkedValues.get(1));
            controller.genValue.setValue(genValues.get(0));
            controller.embarkedValue.setItems(FXCollections.observableList(embarkedValues));
            controller.genValue.setItems(FXCollections.observableList(genValues));
            controller.classeValue.setItems(FXCollections.observableList(booleanValues));
            controller.classeValue.setValue(booleanValues.get(0));
            HBox embarkedBox = new HBox();
            embarkedBox.setAlignment(Pos.CENTER);
            HBox genBox = new HBox();
            genBox.setAlignment(Pos.CENTER);

            embarkedBox.getChildren().add(new Label("embarked"));
            embarkedBox.getChildren().add(this.controller.embarkedValue);
            genBox.getChildren().add(new Label("gender"));
            genBox.getChildren().add(this.controller.genValue);
            controller.vBoxAttributes.getChildren().add(genBox);
            controller.vBoxAttributes.getChildren().add(embarkedBox);
            HBox choice = new HBox();
            choice.setAlignment(Pos.CENTER);
            choice.getChildren().add(new Label("survived"));
            choice.getChildren().add(this.controller.classeValue);
            controller.vBoxAttributes.getChildren().add(choice);
        }



    }
    /**
     * called when the button "ok" is pressed
     * @param actionEvent
     */
    public void handleOk(ActionEvent actionEvent) {
        //handle du bouton ok
        List<String> formulaireValeurs = new ArrayList<>();
        for (TextField tf : formulaires) {
            formulaireValeurs.add(tf.getText());
        }

        if (Main.modele.getBaseDataSet().getTitle().equals("TitanicSet")) {
            formulaireValeurs.add((String) genValue.getValue());
            formulaireValeurs.add((String) embarkedValue.getValue());

        }
        formulaireValeurs.add((String) classeValue.getValue());
        Main.modele.setFormulaireValeurs(formulaireValeurs);
    }
}
