package core.gitobjects;

public class TreeEntry {
	private String hash;
	private String name;

	/**
	 * Construit un TreeEntry a partir de la ligne de caractères désignant cette
	 * entrée dans le tree.
	 * 
	 * @param entryLine
	 *            : Le String désignant l'entrée.
	 */
	public TreeEntry(String entryLine) {
		int firstSpace = entryLine.indexOf(32);
		int secondSpace = entryLine.indexOf(32, firstSpace + 1);
		name = entryLine.substring(firstSpace + 1, secondSpace);
		hash = entryLine.substring(secondSpace + 1);
	}

	public String getHash() {
		return hash;
	}

	public String getName() {
		return name;
	}
}
