package core.gitobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.GitProperty;
import core.GitProperty.PropertyType;


public class Commit extends AbstractGitObject{

	protected Commit(String hash, String rawData) {
		super(hash, rawData);
		this.setType(GitObjectType.Commit);
	}
	
	public void fillData() {
		Pattern lines = Pattern.compile("\n");
		String[] data = lines.split(this.getRawData());
		//Id du tree pointé par le commit
		this.getProperties().add(new GitProperty<String>(PropertyType.TreeId,data[0].substring(5)));
		Tree tree = (Tree) this.getRepository().getObject(data[0].substring(5));
		tree.setParent(this);
		tree.setName("Commit root");
		//Liste des parents du commit
		int i = 1;
		List <String>parentsList = new ArrayList<String>();
		while (data[i].contains("parent")) {
			parentsList.add(data[i].substring(7));
			++ i;
		}
		this.getProperties().add(new GitProperty<List<String>>(PropertyType.ParentList,parentsList));
		//Nom de l'auteur
		Pattern authorName = Pattern.compile("author .+ <");
		Matcher aName = authorName.matcher(data[i]);
		while (aName.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.AuthorName,aName.group().substring(7, aName.group().length() - 2)));
		}
		//Mail de l'auteur
		Pattern authorMail = Pattern.compile("<.*>");
		Matcher aMail = authorMail.matcher(data[i]);
		while (aMail.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.AuthorMail,aMail.group().substring(1, aMail.group().length() - 1)));
		}
		//Date d'édition
		Pattern authorDate = Pattern.compile("[0-9]* \\+[0-9]*");
		Matcher aDate = authorDate.matcher(data[i]);
		while (aDate.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.AuthorDate,aDate.group()));
		}
		++ i;
		//Nom du commiter
		Pattern commiterName = Pattern.compile("committer .+ <");
		Matcher cName = commiterName.matcher(data[i]);
		while (cName.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.CommitterName,cName.group().substring(10, cName.group().length() - 2)));
		}
		//Mail du commiter
		Pattern commiterMail = Pattern.compile("<.*>");
		Matcher cMail = commiterMail.matcher(data[i]);
		while (cMail.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.CommitterMail, cMail.group().substring(1, cMail.group().length() - 1)));
		}
		//Date de commit
		Pattern commiterDate = Pattern.compile("[0-9]* \\+[0-9]*");
		Matcher cDate = commiterDate.matcher(data[i]);
		while (cDate.find()) {
			this.getProperties().add(new GitProperty<String>(PropertyType.CommitterDate, cDate.group()));
		}
	}
}
