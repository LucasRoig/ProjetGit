package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commit extends GitObject {
	
	String treeId;
	ArrayList<String> parentsList;
	String commitAuthor;
	String authorDate;
	String commitCommiter;
	String commiterDate;
	
	public Commit(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Commit;
		this.setDataContent(rawData);
	}
	
	public String getTreeId() {
		return treeId;
	}
	
	public ArrayList<String> getParentsList() {
		return parentsList;
	}
	
	public String getCommitAuthor() {
		return commitAuthor;
	}
	
	public String getAuthorDate() {
		return authorDate;
	}
	
	public String getCommitCommiter() {
		return commitCommiter;
	}
	
	public String getCommiterDate() {
		return commiterDate;
	}
	
	public void setDataContent(String rawData){
		Pattern lines = Pattern.compile("\n");
		String[] data = lines.split(rawData);
		
		treeId = data[0].substring(5);
		
		int i = 1;
		parentsList = new ArrayList<String>();
		while(data[i].contains("parent")){
			parentsList.add(data[i].substring(7));
			i += 1;
		}
		
		Pattern authorName = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");
		Matcher aName = authorName.matcher(data[i]);
		while(aName.find()) {
			commitAuthor = aName.group();
		}
		
		Pattern authorMail = Pattern.compile("<.*>");;
		Matcher aMail = authorMail.matcher(data[i]);
		while(aMail.find()) {
			commitAuthor += " : " + aMail.group().substring(1, aMail.group().length()-1);
		}
		
		Pattern authorDate = Pattern.compile("[0-9]* \\+[0-9]*");
    	Matcher aDate = authorDate.matcher(data[i]);
    	while(aDate.find()) {
    		this.authorDate = aDate.group();
    	}
		
		i += 1;
		
		Pattern commiterName = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");
		Matcher cName = commiterName.matcher(data[i]);
		while(cName.find()) {
			commitCommiter = cName.group();
		}
		
		Pattern commiterMail = Pattern.compile("<.*>");;
		Matcher cMail = commiterMail.matcher(data[i]);
		while(cMail.find()) {
			commitCommiter += " : " + cMail.group().substring(1, cMail.group().length()-1);
		}
		
		Pattern commiterDate = Pattern.compile("[0-9]* \\+[0-9]*");
    	Matcher cDate = commiterDate.matcher(data[i]);
    	while(cDate.find()) {
    		this.commiterDate = cDate.group();
    	}
		
	}

}
