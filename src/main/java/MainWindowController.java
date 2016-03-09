import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.errors.RepositoryNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.RepositoryData;

/**
 * 
 * @author Lucas Roig Controleur de la fenêtre principale
 */
public class MainWindowController {
	private RepositoryData repository;
	private Stage mainStage;
	@FXML
	BorderPane mainPane;
	@FXML
	private MenuItem openFolder;

	@FXML
	private void initialize() {
		openFolder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DirectoryChooser chooser = new DirectoryChooser();
				File choosenDirectory = chooser.showDialog(mainStage);
				try {
					repository = new RepositoryData(choosenDirectory);
					openObjectView();
					// TODO Gérer les autres erreurs
				} catch (RepositoryNotFoundException e) {
					final Alert alert = new Alert(AlertType.ERROR, "Impossible de trouver le dossier .git",
							ButtonType.OK);
					alert.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	/*
	 * Ouvre la vue affichant la liste des objets après l'avoir initialisé
	 */
	private void openObjectView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("fxml/ObjectsView.fxml"));
		AnchorPane objectsView = (AnchorPane) loader.load();
		((ObjectsViewController) (loader.getController())).setRepository(this.repository);
		this.mainPane.setCenter(objectsView);
	}
}
