package model;

public class Blob extends GitObject implements HasName {
	private String name;

	public Blob(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Blob;
	}

	@Override
	public void setDataContent() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
