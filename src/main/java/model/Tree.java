package model;

public class Tree extends GitObject{
	public Tree(String hash, String rawData) {
		super(hash, rawData);
		this.type = GitObjectType.Tree;
	}

}
