package model;

public class TreeEntry {
	private String hash;
	private String name;
	
	/**
	 * Construit un TreeEntry a partir de la ligne de caractères
	 * désignant cette entrée dans le tree.
	 * @param entryLine : Le String désignant l'entrée.
	 */
	public TreeEntry(String entryLine) {
		int firstSpace = entryLine.indexOf(32);
		int secondSpace = entryLine.indexOf(32, firstSpace +1);
		hash = entryLine.substring(firstSpace, secondSpace);
		name = entryLine.substring(secondSpace);
	}
}
