package core.gitobjects;

import java.util.ArrayList;
import java.util.List;

import core.GitProperty;
import core.GitProperty.PropertyType;
import model.TreeEntry;

public class Tree extends AbstractGitObject implements HasName{
	List<TreeEntry> treeEntriesList = new ArrayList<>();
	protected Tree(String hash, byte[] b) {
		super(hash, "");
		this.setType(GitObjectType.Tree);
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
					this.treeEntriesList.add(new TreeEntry(lineBuffer.toString()));
					lineBuffer.setLength(0);
				}
			} else {
				lineBuffer.append((char) by);
				buffer.append((char) by);
			}
		}
		this.setRawData(buffer.toString());
		this.getProperties().add(new GitProperty<List<TreeEntry>>(PropertyType.TreeEntries,treeEntriesList));
	}

	@Override
	public void setName(String name) {
		this.getProperties().add(new GitProperty<String>(PropertyType.Name,name));
	}
	@Override
	public void fillData() {
		for (TreeEntry treeEntry : this.treeEntriesList) {
			IGitObject object = this.getRepository().getObject(treeEntry.getHash());
			if (object != null) {
				((HasName) object).setName(treeEntry.getName());
				object.setParent(this);
			}
		}
	}
}
