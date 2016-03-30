import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.GitObject;
import model.Tag;

public class TagDataController extends ObjectDataController {
	@FXML
	Label objectID;
	
	@FXML
	Label objectType;
	
	@FXML
	Label tagName;
	
	@FXML
	Label tagTagger;
	
	@FXML
	Label tagMail;
	
	@FXML
	Label tagDate;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setDataContent(GitObject object){
		setObjectId(((Tag) object).getObjectId());
		setObjectType(((Tag) object).getObjectType());
		setTagName(((Tag) object).getTagName());
		setTagTagger(((Tag) object).getTagTagger());
		setTagName(((Tag) object).getTagName());
		setTagTagger(((Tag) object).getTagTagger());
		setTagMail(((Tag) object).getTagMail());
		setTagDate(((Tag) object).getTagDate());
        
	}
	
	public void setObjectId(String hex_obj_id){
		this.objectID.setText(hex_obj_id);
	}
	
	public void setObjectType(String obj_type){
		this.objectType.setText(obj_type.substring(0,1).toUpperCase() + obj_type.substring(1));
	}
	
	public void setTagName(String tag_name){
		this.tagName.setText(tag_name);
	}
	
	public void setTagTagger(String safe_name){
		this.tagTagger.setText(safe_name);
	}
	
	public void setTagMail(String safe_email){
		this.tagMail.setText(safe_email.substring(1, safe_email.length()-1));
	}
	
	public void setTagDate(String git_date){
		this.tagDate.setText(gitDateToString(git_date));
	}

}
