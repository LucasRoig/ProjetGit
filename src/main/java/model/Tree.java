package model;

import java.util.ArrayList;

public class Tree extends GitObject {
	private ArrayList<TreeEntry> treeEntriesList = new ArrayList<>();

	public Tree(String hash, byte[] b) {
		super(hash, "");
		this.type = GitObjectType.Tree;

		// Decode les données spécifiques aux trees
		StringBuffer buffer = new StringBuffer();
		StringBuffer lineBuffer = new StringBuffer(); // contient une seule
														// ligne de caractères
		boolean trad = false;
		int cpt = 0;
		for (byte by : b) {
			if (by == 0) {
				if (!trad) {
					buffer.append((char) 32);
					lineBuffer.append((char) 32);
					cpt = 20;
				}
				trad = true;
			} else {
				if (trad) {
					String string = Integer.toHexString(by);
					if (string.length() > 2) // TODO:vérifier l'utilité de cette
												// ligne
						string = string.substring(string.length() - 2);
					buffer.append(string);
					lineBuffer.append(string);
					cpt--;
					if (cpt <= 0) {
						trad = false;
						buffer.append("\n");
						treeEntriesList.add(new TreeEntry(lineBuffer.toString()));
						lineBuffer.setLength(0);
					}
				} else {
					lineBuffer.append((char) by);
					buffer.append((char) by);
				}
			}
		}
		this.setRawData(buffer.toString());
	}

	public ArrayList<TreeEntry> getTreeEntriesList() {
		return treeEntriesList;
	}
}
