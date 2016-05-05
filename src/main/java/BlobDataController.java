import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.GitObject;
import model.HasName;

public class BlobDataController extends ObjectDataController {
	@FXML
	Label blobName;

	@FXML
	Label blobTree;

	@FXML
	private void initialize() {
		blobTree.setUnderline(true);

		blobTree.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent enter) {
				MainApp.getScene().setCursor(Cursor.HAND);
			}
		});

		blobTree.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent enter) {
				MainApp.getScene().setCursor(Cursor.DEFAULT);
			}
		});

		blobTree.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent click) {
				MainWindowController.getObjectsViewController().setSelectedObject(blobTree.getText());
			}
		});
	}

	public void setDataContent(GitObject object) {
		blobName.setText(((HasName) object).getName());
		GitObject parent = object.getParent();
		if (parent != null) {
			blobTree.setText(object.getParent().getHash());
		}
	}
}
