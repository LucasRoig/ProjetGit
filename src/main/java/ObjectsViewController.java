import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.GitObject;

public class ObjectsViewController {
	
	@FXML
	private TableView<GitObject> objectTable;
	@FXML
	private TableColumn<GitObject, String> hashColumn;
	@FXML
	private TableColumn<GitObject, String> typeColumn;
	@FXML
	private TextArea dataText;
	@FXML
	private MainApp mainApp;
	
	public ObjectsViewController() {
		
	}
	
	@FXML
	private void initialize() {
		hashColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHash()));
        showRawData(null);
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
        objectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showRawData(newValue));
	}
	
	private void showRawData(GitObject object) {
		if (object == null) {
			dataText.setText("");
		}
		else {
			dataText.setText(object.getRawData());
		}
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        objectTable.setItems(mainApp.getObjectList());
        
	}

}
