
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.GitObject;

public class ObjectDataFactory {
	static String commitDataPath = "fxml/CommitData.fxml";
	static String treeDataPath = "fxml/TreeData.fxml";
	static String blobDataPath = "fxml/BlobData.fxml";
	static String tagDataPath = "fxml/TagData.fxml";

	public static AnchorPane getObjectData(GitObject object) {
		String path = new String();
		if (object == null) {
			return null;
		} else {
			switch (object.getType()) {
			case Commit:
				path = commitDataPath;
				break;
			case Tree:
				path = treeDataPath;
				break;
			case Blob:
				path = blobDataPath;
				break;
			case Tag:
				path = tagDataPath;
				break;
			}
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(path));
			try {
				AnchorPane objectData = (AnchorPane) loader.load();
				ObjectDataController controller = loader.getController();
				controller.setDataContent(object);
				return objectData;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}

}
