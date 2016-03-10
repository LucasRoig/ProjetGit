import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class BlobDataController {
	@FXML
	TextArea blobDataText;
	
	@FXML
	private void initialize() {
		blobDataText.setText("c'est un Blob");
	}

}
