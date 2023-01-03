package view;

import MVC.Model;
import MVC.ModelClassifier;
import MVC.ModelRobustesse;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class, launches the main page of the application
 */
public class Main extends Application {

    //we put the model in static to be able to access it in all views easily
    public static Model modele = new Model();
    public static ModelClassifier modelClassifier = new ModelClassifier();

    public static ModelRobustesse modelRobustesse = new ModelRobustesse();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView vuePrincipale = new MainView();
        vuePrincipale.start(new Stage());

    }
}
