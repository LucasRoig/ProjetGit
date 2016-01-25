package model;

public class Blob extends GitObject {
	public Blob(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Blob;
	}

}
