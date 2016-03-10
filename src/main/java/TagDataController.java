import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TagDataController {
	@FXML
	TextArea tagDataText;
	
	@FXML
	private void initialize() {
		tagDataText.setText("c'est un Tag");
	}

}
