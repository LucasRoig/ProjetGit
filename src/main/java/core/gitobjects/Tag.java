package core.gitobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.GitProperty;
import core.GitProperty.PropertyType;

public class Tag extends AbstractGitObject{

	protected Tag(String hash, String rawData) {
		super(hash, rawData);
		this.setType(GitObjectType.Tag);
	}
	
	public void fillData(){
		Pattern lines = Pattern.compile("\n");
		String[] data = lines.split(this.getRawData());
		//Id de l'objet pointé par le tag
		this.getProperties().add(new GitProperty<String>(PropertyType.TagChild, data[0].substring(7)));
		//Type de l'objet pointé par le tag
		this.getProperties().add(new GitProperty<String>(PropertyType.Type,data[1].substring(5)));
		//Nom du tag
		this.getProperties().add(new GitProperty<String>(PropertyType.Name,data[2].substring(4)));
		//Nom du tagger
		Pattern taggerName = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");
		Matcher tagger = taggerName.matcher(data[3]);
		while (tagger.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.AuthorName,tagger.group()));
		}
		//Mail du Tagger
		Pattern taggerMail = Pattern.compile("<.*>");
		Matcher mail = taggerMail.matcher(data[3]);
		//TODO : essayer sans le while
		while (mail.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.AuthorMail,mail.group()));
		}
		//Date de création du tag
		Pattern taggerDate = Pattern.compile("[0-9]* \\+[0-9]*");
		Matcher date = taggerDate.matcher(data[3]);
		while (date.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.Date,date.group()));
		}
	}
}
