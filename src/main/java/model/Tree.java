package model;

import java.util.ArrayList;

public class Tree extends GitObject implements HasName {
	private ArrayList<TreeEntry> treeEntriesList = new ArrayList<>();
	private String name;

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
			if (by == 0 && !trad) {
				// FIXME: un commentaire sur pourquoi 32 ne serai pas du luxe !
				// tout le monde ne sait pas forcement que 32 est le code ASCII
				// de l'espace !
				buffer.append((char) 32);
				lineBuffer.append((char) 32);
				cpt = 20;
				trad = true;
			} else if (trad) {
				// FIXME: else if améliore la lisibilité
				String string = Integer.toHexString(by);
				if (string.length() > 2) {
					string = string.substring(string.length() - 2);
				} else if (string.length() == 1) {
					string = "0" + string;
				}
				buffer.append(string);
				lineBuffer.append(string);
				// FIXME: préférez une pré (in/dé)crémentation qu'une post.
				// pour des raisons de perf (même minime) et éviter de futur bug
				// si jamais...
				--cpt;
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
		this.setRawData(buffer.toString());

	}

	public ArrayList<TreeEntry> getTreeEntriesList() {
		return treeEntriesList;
	}

	@Override
	public void setDataContent() {
		for (TreeEntry treeEntry : this.getTreeEntriesList()) {
			GitObject object = this.getRepositoryData().getObjectByHash(treeEntry.getHash());
			if (object != null) {
				((HasName) object).setName(treeEntry.getName());
				object.setParent(this);
			}
		}
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setDeletable(boolean isDeletable) {
		super.setDeletable(isDeletable);
		for (TreeEntry treeEntry : treeEntriesList) {
			GitObject object = this.getRepositoryData().getObjectByHash(treeEntry.getHash());
			if (object != null) {
				object.setDeletable(isDeletable);
			}
		}
	}
}
