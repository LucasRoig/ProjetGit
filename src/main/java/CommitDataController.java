
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Commit;
import model.GitObject;

public class CommitDataController extends ObjectDataController {
	@FXML
	AnchorPane commitPane;

	@FXML
	Label treeId;

	@FXML
	ListView<String> parentsList;

	@FXML
	Label commitAuthor;

	@FXML
	Label authorDate;

	@FXML
	Label commitCommitter;

	@FXML
	Label committerDate;

	@FXML
	private void initialize() {
		treeId.setUnderline(true);

		treeId.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent enter) {
				MainApp.getScene().setCursor(Cursor.HAND);
			}
		});

		treeId.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent enter) {
				MainApp.getScene().setCursor(Cursor.DEFAULT);
			}
		});

		parentsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					MainWindowController.getObjectsViewController()
							.setSelectedObject(parentsList.getSelectionModel().getSelectedItem());
				}
			}
		});

		treeId.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent click) {
				MainWindowController.getObjectsViewController().setSelectedObject(treeId.getText());
			}
		});
	}

	public void setDataContent(GitObject object) {
		// FIXME: castez votre objet 1 seule fois: cela aide à la lisibilité.
		Commit commit = (Commit) object;
		setTreeId(commit.getTreeId());
		setParentsList(commit.getParentsList());
		String author = (commit.getCommitAuthor() + " : " + commit.getAuthorMail());
		setCommitAuthor(author);
		setAuthorDate(commit.getAuthorDate());
		String committer = (commit.getCommitCommitter() + " : " + commit.getCommitterMail());
		setCommitCommitter(committer);
		setCommitterDate(commit.getCommitterDate());
	}

	public void setTreeId(String chaine) {
		this.treeId.setText(chaine);
	}

	public void setParentsList(ArrayList<String> listeChaines) {
		ObservableList<String> observableList = FXCollections.observableArrayList();
		observableList.setAll(listeChaines);
		parentsList.setItems(observableList);
	}

	public void setCommitAuthor(String chaine) {
		this.commitAuthor.setText(chaine);
	}

	public void setAuthorDate(String chaine) {
		this.authorDate.setText(gitDateToString(chaine));
	}

	public void setCommitCommitter(String chaine) {
		this.commitCommitter.setText(chaine);
	}

	public void setCommitterDate(String chaine) {
		this.committerDate.setText(gitDateToString(chaine));
	}

}
