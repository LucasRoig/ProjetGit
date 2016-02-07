
import java.io.IOException;
import java.util.Collection;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GitObject;
import model.RepositoryData;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane mainWindow;

	public MainApp() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Projet Git");
		this.primaryStage.show();
		
		initMainWindow();	
	}
	
	public void initMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/MainWindow.fxml"));
            mainWindow = (BorderPane) loader.load();
            Scene scene = new Scene(mainWindow);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
