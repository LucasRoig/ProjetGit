import java.util.ArrayList;

import core.gitobjects.HasName;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.GitObject;
import model.Tree;
import model.TreeEntry;

public class TreeDataController extends ObjectDataController {
	@FXML
	Label treeName;

	@FXML
	TableView<TreeEntry> objectTable;

	@FXML
	TableColumn<TreeEntry, String> nameColumn;

	@FXML
	TableColumn<TreeEntry, String> idColumn;

	@FXML
	private void initialize() {
		objectTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					MainWindowController.getObjectsViewController()
							.setSelectedObject(objectTable.getSelectionModel().getSelectedItem());
				}
			}
		});
	}

	public void setDataContent(GitObject object) {
		setObjectTable(((Tree) object).getTreeEntriesList());
		treeName.setText(((HasName) object).getName());

	}

	public void setObjectTable(ArrayList<TreeEntry> listeChaines) {
		ObservableList<TreeEntry> observableList = FXCollections.observableArrayList();
		observableList.setAll(listeChaines);
		objectTable.setItems(observableList);
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHash()));

	}

}
