import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.GitObject;

public class TreeDataController extends ObjectDataController{
	@FXML
	TableView<?> objectTable;
	
	@FXML
	TableColumn<?, ?> nameColumn;
	
	@FXML
	TableColumn<?, ?> idColumn;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setDataContent(GitObject object){
		
	}

}
