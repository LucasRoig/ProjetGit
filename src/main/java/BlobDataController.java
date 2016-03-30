import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.GitObject;

public class BlobDataController extends ObjectDataController{
	@FXML
	TextArea blobDataText;
	
	@FXML
	private void initialize() {
		blobDataText.setText("c'est un Blob");
	}
	
	public void setDataContent(GitObject object){
		
	}

}
