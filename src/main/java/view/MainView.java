package view;

import MVC.Observer;
import MVC.Subject;
import both.DataSet;
import dataInterfaces.IPoint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * displays and manages the main page
 */
public class MainView extends Application implements Observer {

    private static final int UNDEFINED = -1;
    @FXML
    static int nbScatterChartSeries;
    private final StartMainView startMainView = new StartMainView(this);

    public DataSetClassifier getDataSetClassifier() {
        return dataSetClassifier;
    }

    private final DataSetClassifier dataSetClassifier = new DataSetClassifier(this);
    private final UpdateMainView updateMainView = new UpdateMainView(this);

    public void setController(MainView controller) {
        this.controller = controller;
    }

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

    public DataSet getBaseDataSet() {
        return baseDataSet;
    }

    public void setBaseDataSet(DataSet baseDataSet) {
        this.baseDataSet = baseDataSet;
    }

    DataSet baseDataSet;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    Scene scene;
    @FXML
    VBox scatterVBox;

    @FXML Button importerButton;

    public static int sliderValueInt=0;

    public static String title="";

    public SliderValue getSliderValue() {
        return sliderValue;
    }

    SliderValue sliderValue = new SliderValue(this);

    public CreateChart getCreateChart() {
        return createChart;
    }

    CreateChart createChart = new CreateChart(this);
    SelectPointChart selectPointChart = new SelectPointChart(this);

    public double getRobustnessValue() {
        return robustnessValue;
    }

    double robustnessValue;

    public void setRobustnessValue(double val) {
        this.robustnessValue=val;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * displays the page
     *
     * @param mainStage the primary stage for this application, onto which
     *                  the application scene can be set.
     */
    @Override
    public void start(Stage mainStage){
        try {
            startMainView.start(mainStage);
            mainStage.getScene().getWindow().setOnCloseRequest(e -> {
                String errorMessage = "Aurevoir Billy :)";
                Dialog d = new Alert(Alert.AlertType.INFORMATION, errorMessage);
                d.show();
            });
        } catch (Exception e) {
            String errorMessage = "tu as fait de la merde billy !";
            Dialog d = new Alert(Alert.AlertType.ERROR, errorMessage);
            d.show();
        }
    }

    public void handleSlider(double number, Scene scene) {
        try {
            this.sliderValue.sliderCss(number, scene);
        } catch (Exception e) {
            String errorMessage = "tu as fait de la merde billy !";
            Dialog d = new Alert(Alert.AlertType.ERROR, errorMessage);
            d.show();
            e.printStackTrace();
        }
    }

    /**
     * is called when the coverage / classify button is pressed
     */
    public void handleCoverageButtonAction(ActionEvent actionEvent) throws Exception {

        if (coverageButton.getText().equals("Coverage")) {
            progressBar.setVisible(true);
            ChooseClassParamView chooseClassParamViewRobustesse = new ChooseClassParamView();
            chooseClassParamViewRobustesse.start(new Stage());
        } else if (coverageButton.getText().equals("Classify")) {
            ChooseClassParamView chooseClassParamViewClassify = new ChooseClassParamView();
            chooseClassParamViewClassify.start(new Stage());
        }
    }

    /**
     * is called when the ajouter button is pressed --> displays a form to submit in order to create a new point
     */
    public void handleAjouterButtonAction(ActionEvent actionEvent) throws Exception {
        AddPointView addPointView = new AddPointView();
        addPointView.setMainView(this);
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
        updateMainView.update(subj);
    }

    public void createDataSetClassifier(int val) {
        dataSetClassifier.createDataSetClassifier(val);
    }

    void setListenerClickSeries(XYChart.Series series1, int val) {
        this.selectPointChart.selectPointChart(series1, val);
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

    void addMouseoverGlow(NumberAxis n) {
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

    @FXML
    public void handleScroll(ScrollEvent event) {
        createChart.handleScrollChart(event);
    }

}