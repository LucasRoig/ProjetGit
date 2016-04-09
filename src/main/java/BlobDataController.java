import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.GitObject;

public class BlobDataController extends ObjectDataController {
	@FXML
	Label blobName;

	@FXML
	Label blobTree;

	@FXML
	private void initialize() {
		blobTree.setUnderline(true);
	}

	public void setDataContent(GitObject object) {

	}

}
