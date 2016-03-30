import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.GitObject;
import model.Commit;

public class CommitDataController extends ObjectDataController{
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
	Label commitCommiter;
	
	@FXML
	Label commiterDate;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setDataContent(GitObject object){
		setTreeId(((Commit) object).getTreeId());
		setParentsList(((Commit) object).getParentsList());
		setCommitAuthor(((Commit) object).getCommitAuthor());
		setAuthorDate(((Commit) object).getAuthorDate());
		setCommitCommiter(((Commit) object).getCommitCommiter());
		setCommiterDate(((Commit) object).getCommiterDate());
	}
	
	public void setTreeId(String obj_hex_id){
		this.treeId.setText(obj_hex_id);
	}
	
	public void setParentsList(ArrayList<String> obj_hex_ids){
		ObservableList<String> observableList = FXCollections.observableArrayList();
		observableList.setAll(obj_hex_ids);
        parentsList.setItems(observableList);
	}
	
	public void setCommitAuthor(String safe_name){
		this.commitAuthor.setText(safe_name);
	}
	
	public void setAuthorDate(String git_date){
		this.authorDate.setText(gitDateToString(git_date));
	}
	
	public void setCommitCommiter(String safe_name){
		this.commitCommiter.setText(safe_name);
	}
	
	public void setCommiterDate(String git_date){
		this.commiterDate.setText(gitDateToString(git_date));
	}

}
