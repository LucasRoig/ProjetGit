import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.GitObject;

public class TreeDataController extends ObjectDataController{
	@FXML
	TextArea treeDataText;
	
	@FXML
	private void initialize() {
		treeDataText.setText("c'est un Tree");
	}
	
	public void setDataContent(GitObject object){
		
	}

}
