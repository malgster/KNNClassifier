package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PagePrincipale extends Application{

	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		String fileName= Paths.get(".").normalize().toAbsolutePath()+ File.separator+"IHM/user_interface.fxml";
		URL fxmlFileUrl = getClass().getResource(fileName);
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("FXML demo");
		stage.show();
	}


	public static void main(String[] args) {
		Application.launch(args);
	}

}
