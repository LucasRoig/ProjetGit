import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.GitObject;
import model.hasName;

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
		blobName.setText(((hasName) object).getName());
		GitObject parent = object.getParent();
		if (parent != null) {
			blobTree.setText(object.getParent().getHash());
		}
	}
}
