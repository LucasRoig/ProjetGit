
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
	private ObservableList<GitObject> objectList = FXCollections.observableArrayList();

	public MainApp() {
		try {
			RepositoryData rep = new RepositoryData("/Users/PercyStrobb/jgit-cookbook");
			Collection<GitObject> objects = rep.getObjectList();
			for (GitObject i : objects) {
				objectList.add(i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Projet Git");
		this.primaryStage.show();
		
		initMainWindow();
		showObjectsView();
		
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
	
	private void showObjectsView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("fxml/ObjectsView.fxml"));
            AnchorPane objectsView = (AnchorPane) loader.load();
            mainWindow.setCenter(objectsView);
            ObjectsViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public ObservableList<GitObject> getObjectList() {
		return objectList;
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
