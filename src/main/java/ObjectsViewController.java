import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.GitObject;
import model.RepositoryData;

public class ObjectsViewController {

	private RepositoryData repository;
	private ObservableList<GitObject> objectList = FXCollections.observableArrayList();

	@FXML
	private TableView<GitObject> objectTable;
	@FXML
	private TableColumn<GitObject, String> hashColumn;
	@FXML
	private TableColumn<GitObject, String> typeColumn;
	@FXML
	private TextArea dataText;
	@FXML
	private TextField filterTextField;
	@FXML
	private BorderPane dataPane;

	@FXML
	private void initialize() throws IOException {
		FilteredList<GitObject> filteredList = new FilteredList<>(this.objectList, p -> true);

		filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(object -> {
				return newValue == null || newValue.isEmpty() || object.getHash().startsWith(newValue.toLowerCase());
			});
		});

		SortedList<GitObject> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(objectTable.comparatorProperty());
		objectTable.setItems(sortedList);
		hashColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHash()));

		typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));

		objectTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showRawData(newValue));
		showRawData(null);

		objectTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> openObjectData(newValue));
		openObjectData(null);
	}

	private void showRawData(GitObject object) {
		if (object == null) {
			dataText.setText("");
		} else {
			dataText.setText(object.getRawData());
		}
	}

	public void setRepository(RepositoryData repository) {
		this.repository = repository;
		this.objectList.clear();
		this.objectList.addAll(this.repository.getObjectList());
	}

	private void openObjectData(GitObject object) {
		if (object == null) {
			this.dataPane.setCenter(new AnchorPane());
		} else {
			dataPane.getChildren().clear();
			dataPane.setCenter(ObjectDataFactory.getObjectData(object));
		}
	}

	public void setSelectedObject(String objectId) {
		GitObject object = repository.getObjectByHash(objectId);
		objectTable.getSelectionModel().select(object);
	}
}
