package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tag extends GitObject {
	String objectId;
	String objectType;
	String tagName;
	String tagTagger;
	String tagMail;
	String tagDate;

	public Tag(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Tag;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public String getTagName() {
		return tagName;
	}

	public String getTagTagger() {
		return tagTagger;
	}

	public String getTagMail() {
		return tagMail;
	}

	public String getTagDate() {
		return tagDate;
	}

	public void setDataContent() {
		Pattern lines = Pattern.compile("\n");
		String[] data = lines.split(this.rawData);

		objectId = data[0].substring(7);

		objectType = data[1].substring(5);

		tagName = data[2].substring(4);

		Pattern taggerName = Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+");
		Matcher tagger = taggerName.matcher(data[3]);
		while (tagger.find()) {
			tagTagger = tagger.group();
		}

		Pattern taggerMail = Pattern.compile("<.*>");
		Matcher mail = taggerMail.matcher(data[3]);
		while (mail.find()) {
			tagMail = mail.group();
		}

		Pattern taggerDate = Pattern.compile("[0-9]* \\+[0-9]*");
		Matcher date = taggerDate.matcher(data[3]);
		while (date.find()) {
			tagDate = date.group();
		}

	}
}
