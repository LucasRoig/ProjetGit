package core.gitobjects;

import core.GitProperty;
import core.GitProperty.PropertyType;

public class Blob extends AbstractGitObject implements HasName{
	public Blob(String hash,String rawData){
		super(hash,rawData);
		this.setType(GitObjectType.Blob);
	}
	
	@Override
	public void setName(String name) {
		this.getProperties().add(new GitProperty<String>(PropertyType.Name, name));
	}
}
