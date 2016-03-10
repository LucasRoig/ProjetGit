import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TreeDataController {
	@FXML
	TextArea treeDataText;
	
	@FXML
	private void initialize() {
		treeDataText.setText("c'est un Tree");
	}

}
