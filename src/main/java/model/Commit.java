package model;

public class Commit extends GitObject {
	public Commit(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Commit;
	}

}
