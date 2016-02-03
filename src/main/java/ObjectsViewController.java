import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
	
	public ObjectsViewController() {
		
	}
	
	@FXML
	private void initialize() {
		objectTable.setItems(objectList);
		hashColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHash()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
        objectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showRawData(newValue));
        showRawData(null);
	}
	
	private void showRawData(GitObject object) {
		if (object == null) {
			dataText.setText("");
		}
		else {
			dataText.setText(object.getRawData());
		}
	}
	
	public void setRepository(RepositoryData repository) {
		this.repository = repository;
		this.objectList.clear();
		this.objectList.addAll(this.repository.getObjectList());
	}
}
