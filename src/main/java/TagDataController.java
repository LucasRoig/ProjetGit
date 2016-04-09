import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.GitObject;
import model.Tag;

public class TagDataController extends ObjectDataController {
	@FXML
	Label objectId;
	
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
		objectId.setUnderline(true);
		
		objectId.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent enter) {
				MainApp.getScene().setCursor(Cursor.HAND);
			}
		});
		
		objectId.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent enter) {
				MainApp.getScene().setCursor(Cursor.DEFAULT);
			}
		});
		
		objectId.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent click) {
		        MainWindowController.getObjectsViewController().setSelectedObject(objectId.getText());
			}
		});
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
	
	public void setObjectId(String chaine){
		this.objectId.setText(chaine);
	}
	
	public void setObjectType(String chaine){
		this.objectType.setText(chaine.substring(0,1).toUpperCase() + chaine.substring(1));
	}
	
	public void setTagName(String chaine){
		this.tagName.setText(chaine);
	}
	
	public void setTagTagger(String chaine){
		this.tagTagger.setText(chaine);
	}
	
	public void setTagMail(String chaine){
		this.tagMail.setText(chaine.substring(1, chaine.length()-1));
	}
	
	public void setTagDate(String chaine){
		this.tagDate.setText(gitDateToString(chaine));
	}

}
