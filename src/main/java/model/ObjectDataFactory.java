package model;

public class ObjectDataFactory {
	String commitDataPath = "fxml/CommitData.fxml";
	String treeDataPath = "fxml/TreeData.fxml";
	String blobDataPath = "fxml/BlobData.fxml";
	String tagDataPath = "fxml/TagData.fxml";
	
	public String getObjectData(GitObject object){
		if (object == null){
			return null;
		}
		else{
			switch(object.getType()){
			case Commit:
				return commitDataPath;
			case Tree:
				return treeDataPath;
			case Blob:
				return blobDataPath;
			case Tag:
				return tagDataPath;
			}
		return null;
		}
	}

}
