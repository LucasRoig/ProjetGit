package model;

public class Tag extends GitObject {
	public Tag(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Tag;
	}
}
