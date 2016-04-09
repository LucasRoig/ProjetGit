package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commit extends GitObject {

	String treeId;
	ArrayList<String> parentsList;
	String commitAuthor;
	String authorMail;
	String authorDate;
	String commitCommitter;
	String committerMail;
	String committerDate;

	public Commit(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Commit;
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

	public String getAuthorMail() {
		return authorMail;
	}

	public String getAuthorDate() {
		return authorDate;
	}

	public String getCommitCommitter() {
		return commitCommitter;
	}

	public String getCommitterMail() {
		return committerMail;
	}

	public String getCommitterDate() {
		return committerDate;
	}

	public void setDataContent() {
		Pattern lines = Pattern.compile("\n");
		String[] data = lines.split(this.rawData);

		treeId = data[0].substring(5);

		int i = 1;
		parentsList = new ArrayList<String>();
		while (data[i].contains("parent")) {
			parentsList.add(data[i].substring(7));
			i += 1;
		}

		Pattern authorName = Pattern.compile("author .+ <");
		Matcher aName = authorName.matcher(data[i]);
		while (aName.find()) {
			this.commitAuthor = aName.group().substring(7, aName.group().length() - 2);
		}

		Pattern authorMail = Pattern.compile("<.*>");
		;
		Matcher aMail = authorMail.matcher(data[i]);
		while (aMail.find()) {
			this.authorMail = aMail.group().substring(1, aMail.group().length() - 1);
		}

		Pattern authorDate = Pattern.compile("[0-9]* \\+[0-9]*");
		Matcher aDate = authorDate.matcher(data[i]);
		while (aDate.find()) {
			this.authorDate = aDate.group();
		}

		i += 1;

		Pattern commiterName = Pattern.compile("committer .+ <");
		Matcher cName = commiterName.matcher(data[i]);
		while (cName.find()) {
			commitCommitter = cName.group().substring(10, cName.group().length() - 2);
		}

		Pattern commiterMail = Pattern.compile("<.*>");
		;
		Matcher cMail = commiterMail.matcher(data[i]);
		while (cMail.find()) {
			this.committerMail = cMail.group().substring(1, cMail.group().length() - 1);
		}

		Pattern commiterDate = Pattern.compile("[0-9]* \\+[0-9]*");
		Matcher cDate = commiterDate.matcher(data[i]);
		while (cDate.find()) {
			this.committerDate = cDate.group();
		}

	}

}
