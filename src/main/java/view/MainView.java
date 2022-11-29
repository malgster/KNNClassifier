package view;

import MVC.Observer;
import MVC.Subject;
import dataInterfaces.IColumn;
import dataInterfaces.IPoint;
import iris.IrisVariety;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * displays and manages the main page
 */
public class MainView extends Application implements Observer {

    private static final int UNDEFINED = -1;
    @FXML
    static int nbScatterChartSeries;
    MainView controller;
    @FXML
    ListView<IPoint> pointListeView;
    @FXML
    Label titleLabel;
    @FXML
    NumberAxis ordonneLabel;
    @FXML
    NumberAxis abscisseLabel;
    @FXML
    Slider slider;
    @FXML
    ProgressBar progressBar;
    @FXML
    Button coverageButton;
    @FXML
    ScatterChart scatterChart;
    @FXML
    ListView listeSelected;
    @FXML
    Label selectedPointLabel;

    Scene scene;
    @FXML
    VBox scatterVBox;

    SliderValue sliderValue = new SliderValue(this);
    CreateChart createChart = new CreateChart(this);
    SelectPointChart selectPointChart = new SelectPointChart(this);

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * displays the page
     *
     * @param MainStage the primary stage for this application, onto which
     *                  the application scene can be set.
     */
    @Override
    public void start(Stage MainStage) throws Exception {
        //Chargement du fichier fxml de l'interface principale
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ihm/interfaces/Main.fxml"));
        root = loader.load();
        controller = loader.getController();
        //initialisation de la Scene
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("ihm/styles/dataset.css").toExternalForm());
        //paramétrage du stage
        MainStage.setTitle("Classifier");
        MainStage.setResizable(false);
        MainStage.setScene(scene);
        controller.progressBar.setVisible(false);
        Main.modele.attach(this);
        Main.modelClassifier.attach(this);
        controller.slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                handleSlider(controller.slider.getValue(), scene);
            }
        });
        //affichage de l'interface
        MainStage.show();
    }

    public void handleSlider(double number, Scene scene) {
        try {
            this.sliderValue.sliderCss(number, scene);
        } catch (Exception e) {
            String errorMessage = "tu as fait de la merde billy";
            Dialog d = new Alert(Alert.AlertType.ERROR, errorMessage);
            d.show();
        }
    }

    /**
     * is called when the Importer button is called, to import a csv
     */
    public void handleImporterButtonAction(javafx.event.ActionEvent actionEvent) {
        try {
            this.sliderValue.sliderValues();
        } catch (Exception e) {
            String errorMessage = "tu as fait de la merde billy";
            Dialog d = new Alert(Alert.AlertType.ERROR, errorMessage);
            d.show();
        }

    }


    /**
     * is called when the coverage button is pressed
     */
    public void handleCoverageButtonAction(ActionEvent actionEvent) {
        if (slider.getValue() == 2) {
            controller.progressBar.setVisible(true);
        }
        if (coverageButton.getText().equals("Coverage")) {

        } else if (coverageButton.getText().equals("Classify")) {

        }
    }

    /**
     * is called when the ajouter button is pressed --> displays a form to submit in order to create a new point
     */
    public void handleAjouterButtonAction(ActionEvent actionEvent) throws Exception {
        AddPointView addPointView = new AddPointView();
        addPointView.start(new Stage());
    }

    public void handleSupprimerButtonAction(ActionEvent actionEvent) {
    }

    /**
     * is called when the classifier label is clicked to choose which type of points to analyse
     */
    @FXML
    public void handleTitleLabelClicked(MouseEvent event) throws Exception {
        launchImporterView();
    }

    /**
     * launches the classifier view
     */
    public void launchImporterView() throws Exception {
        ImporterView importerView = new ImporterView();
        importerView.start(new Stage());

    }

    /**
     * override method object from the Observer interface
     * looks at the model to be updated
     */
    @Override
    public void update(Subject subj) {
        // initialise la listeView avec les points du dataset
        if (controller.slider.getValue() == 0) {
            List<IPoint> values = (List<IPoint>) Main.modele.getBaseDataSet().getMyPoints();
            controller.pointListeView.setItems(FXCollections.observableList(values));

            if (controller.titleLabel.getText().equals("Iris")) {
                createDataSetIris();
            } else if (controller.titleLabel.getText().equals("Titanic")) {
                createDataSetTitanic();
            }

        } else if (controller.slider.getValue() == 1) {
            List<IPoint> values = (List<IPoint>) Main.modelClassifier.getBaseDataSet().getMyPoints();
            controller.pointListeView.setItems(FXCollections.observableList(values));

            createDataSetClassifier();
        }


        controller.scatterChart.setTitle(Main.modele.getBaseDataSet().getTitle());
        controller.titleLabel.setText(Main.modele.getLblTitle());
        nbScatterChartSeries = controller.scatterChart.getData().size();
        addMouseoverGlow(controller.abscisseLabel);
        addMouseoverGlow(controller.ordonneLabel);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Z) {
                controller.ordonneLabel.setAutoRanging(false);
                controller.ordonneLabel.setLowerBound(controller.ordonneLabel.getLowerBound() + 0.1);
                controller.ordonneLabel.setUpperBound(controller.ordonneLabel.getUpperBound() + 0.1);
            } else if (e.getCode() == KeyCode.S) {
                controller.ordonneLabel.setAutoRanging(false);
                controller.ordonneLabel.setLowerBound(controller.ordonneLabel.getLowerBound() - 0.1);
                controller.ordonneLabel.setUpperBound(controller.ordonneLabel.getUpperBound() - 0.1);
            } else if (e.getCode() == KeyCode.D) {
                controller.abscisseLabel.setAutoRanging(false);
                controller.abscisseLabel.setLowerBound(controller.abscisseLabel.getLowerBound() + 0.1);
                controller.abscisseLabel.setUpperBound(controller.abscisseLabel.getUpperBound() + 0.1);
            } else if (e.getCode() == KeyCode.Q) {
                controller.abscisseLabel.setAutoRanging(false);
                controller.abscisseLabel.setLowerBound(controller.abscisseLabel.getLowerBound() - 0.1);
                controller.abscisseLabel.setUpperBound(controller.abscisseLabel.getUpperBound() - 0.1);
            }
        });
    }

    private void createDataSetClassifier() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Not Classifier Points");

        for (IPoint myPoint : Main.modelClassifier.getBaseDataSet().getMyPoints()) {
            series1.getData().add(
                    new XYChart.Data(
                            myPoint.getNormalizedValue(Main.modelClassifier.getBaseDataSet().getColumn(Main.modelClassifier.getAxeY().getName())),
                            myPoint.getNormalizedValue(Main.modelClassifier.getBaseDataSet().getColumn(Main.modelClassifier.getAxeY().getName())),
                            myPoint
                    )
            );
        }
        controller.scatterChart.getData().add(series1);
        controller.abscisseLabel.setLabel(Main.modelClassifier.getAxeX().getName());
        controller.ordonneLabel.setLabel(Main.modelClassifier.getAxeY().getName());
        setListenerClickSeries(series1);
    }

    /**
     * creates a titanic data set
     */
    private void createDataSetTitanic() {
        this.createChart.createChartTitanic();
    }


    /**
     * creates an iris dataset
     */
    private void createDataSetIris() {
        this.createChart.createChartIris();
    }

    void setListenerClickSeries(XYChart.Series series1) {
        this.selectPointChart.selectPointChart(series1);
    }

    /**
     * is called when an element is selected in the listView
     */
    public void handleSelected(MouseEvent mouseEvent) {
        selectPointChart.selectPointList();
    }


    /**
     * create a glow feedback effect on a node when the mouse is hovered over it
     */

    private void addMouseoverGlow(NumberAxis n) {
        createChart.mouseOverGlow(n);
    }

    /**
     * called when the x-axis is clicked
     */
    @FXML
    public void handleXClicked(MouseEvent mouseEvent) throws Exception {
        AttributeXView attributeXView = new AttributeXView();
        attributeXView.start(new Stage());
    }

    /**
     * called when the y-axis is clicked
     */
    @FXML
    public void handleYClicked(MouseEvent mouseEvent) throws Exception {
        AttributeYView attributeYView = new AttributeYView();
        attributeYView.start(new Stage());
    }

    /**
     * update with the classifier label
     */
    @Override
    @FXML
    public void update(Subject subj, Object data) {
        controller.titleLabel.setText(data.toString());
    }

    public MainView getController() {
        return controller;
    }


    // records a relative point location.
    class Delta {
        double x = UNDEFINED, y = UNDEFINED;
    }


    @FXML
    public void handleScroll(ScrollEvent event) {
        if(event.isControlDown()) {
            if(titleLabel.getText().equals("Iris")) {
                this.createChart.createChartIrisHorsFXML();
            }else if(titleLabel.getText().equals("Titanic")){
                this.createChart.createChartTitanicHorsFXML();
            }
        }else{
            double minScale = 1;
            double maxScale = 1.2;
            double scale = abscisseLabel.getScale();
            scale = clamp(scale, minScale, maxScale);
            if(event.isAltDown()){
                scale=scale/1.5d;
            }
            abscisseLabel.setAutoRanging(false);
            abscisseLabel.setLowerBound(0);
            abscisseLabel.setUpperBound(abscisseLabel.getUpperBound()/scale);
            abscisseLabel.setTickUnit(abscisseLabel.getTickUnit()/scale);
            ordonneLabel.setAutoRanging(false);
            ordonneLabel.setLowerBound(0);
            ordonneLabel.setUpperBound(ordonneLabel.getUpperBound()/scale);
            ordonneLabel.setTickUnit(ordonneLabel.getTickUnit()/scale);
        }

    }

    @FXML
    public void handlePressed(MouseEvent event) {

        /*
        if(event.isAltDown()){

        }else if(event.isShiftDown()){
            abscisseLabel.setAutoRanging(true);
            ordonneLabel.setAutoRanging(true);
        }
        if(event.isControlDown()){

        }
        */


    }





    public static double clamp(double value, double min, double max) {

        if (Double.compare(value, min) < 0)
            return min;

        if (Double.compare(value, max) > 0)
            return max;

        return value;
    }

}