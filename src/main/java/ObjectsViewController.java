import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Commit;
import model.GitObject;
import model.GitObjectType;
import model.RepositoryData;
import model.Tree;
import model.TreeEntry;
import model.hasName;
import model.TreeEntry;

public class ObjectsViewController {

	private RepositoryData repository;
	private ObservableList<GitObject> objectList = FXCollections.observableArrayList();

	@FXML
	private CheckBox checkBoxGarbage;
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TableView<GitObject> objectTable;
	@FXML
	private TableColumn<GitObject, String> hashColumn;
	@FXML
	private TableColumn<GitObject, String> typeColumn;
	@FXML
	private TextArea dataText;
	@FXML
	private TextField filterTextField;
	@FXML
	private BorderPane dataPane;

	@FXML
	private void initialize() throws IOException {
		FilteredList<GitObject> filteredList = new FilteredList<>(this.objectList, p -> true);

		checkBoxGarbage.selectedProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(objet -> {
				return (newValue == false || objet.isDeletable())
						&& (filterTextField.getText() == null || filterTextField.getText().isEmpty()
								|| objet.getHash().startsWith(filterTextField.getText().toLowerCase()));
			});
		});

		filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(object -> {
				return (newValue == null || newValue.isEmpty() || object.getHash().startsWith(newValue.toLowerCase()))
						&& (!checkBoxGarbage.isSelected() || object.isDeletable());
			});
		});

		SortedList<GitObject> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(objectTable.comparatorProperty());
		objectTable.setItems(sortedList);
		hashColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHash()));

		typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));

		objectTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showRawData(newValue));
		showRawData(null);

		objectTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> openObjectData(newValue));
		openObjectData(null);

		objectTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> fillTree(newValue));
		fillTree(null);
	}

	private void showRawData(GitObject object) {
		if (object == null) {
			dataText.setText("");
		} else {
			dataText.setText(object.getRawData());
		}
	}

	public void setRepository(RepositoryData repository) {
		this.repository = repository;
		this.objectList.clear();
		this.objectList.addAll(this.repository.getObjectList());
	}

	private void openObjectData(GitObject object) {
		if (object == null) {
			this.dataPane.setCenter(new AnchorPane());
		} else {
			dataPane.getChildren().clear();
			dataPane.setCenter(ObjectDataFactory.getObjectData(object));
		}
	}

	/**
	 * Génère l'arbre afficher dans l'onglet arbre, il présente le contenu du
	 * commit contenant l'objet sélectionné.
	 * 
	 * @param object
	 *            - L'objet dont on souhaite afficher le commit.
	 */
	private void fillTree(GitObject object) {
		if (object != null) {
			TreeItem<String> root = new TreeItem<>();
			try {
				while (object.getType() != GitObjectType.Commit) {
					object = object.getParent();
				}
			} catch (NullPointerException e) {
				// TODO : informer l'utilisateur qu'il y a eu une erreur.
				return;
			}
			root.setValue("Commit " + object.getHash());
			addTreeToNode((Tree) repository.getObjectByHash(((Commit) object).getTreeId()), root);
			treeView.setRoot(root);
		}
	}

	/**
	 * Ajoute chaque TreeEntry d'un objet Tree à un TreeItem
	 * 
	 * @param tree
	 *            - l'objet Tree dont on souhaite ajouter les TreeEntries.
	 * @param node
	 *            - le TreeItem que l'on souhaite modifier.
	 */
	private void addTreeToNode(Tree tree, TreeItem<String> node) {
		for (TreeEntry treeEntry : tree.getTreeEntriesList()) {
			TreeItem<String> newNode = new TreeItem<String>();
			GitObject object = this.repository.getObjectByHash(treeEntry.getHash());
			newNode.setValue(((hasName) object).getName());
			node.getChildren().add(newNode);
			if (object.getType() == GitObjectType.Tree) {
				addTreeToNode((Tree) object, newNode);
			}
		}
	}

	public void setSelectedObject(String objectId) {
		GitObject object = repository.getObjectByHash(objectId);
		objectTable.getSelectionModel().select(object);
	}
	
	public void setSelectedObject(TreeEntry treeEntry) {
		GitObject object = repository.getObjectByHash(treeEntry.getHash());
		objectTable.getSelectionModel().select(object);
	}
}
