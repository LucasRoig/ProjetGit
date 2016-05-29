package core.gitobjects;

import java.util.List;

import core.GitProperty;

public interface IGitObject {
	/**
	 * Retourne le type de l'objet.
	 * @return le type de l'objet.
	 */
	public GitObjectType getType();
	
	/**
	 * Retourne la liste de toutes les propriétés de l'objets.
	 * @return la liste de toutes les propriétés de l'objets.
	 */
	public List<GitProperty<?>> getProperties();
	
	/**
	 * Retourne les données brutes d'un objet git.
	 * @return les données brutes d'un objet git.
	 */
	public String getRawData();
	
	/**
	 * Retourne la clef de hash de l'objet.
	 * @return la clef de hash de l'objet.
	 */
	public String getHash();
	
	/**
	 * Demande à l'objet de générer toutes les GitProperty qui forment ses données.
	 */
	public void fillData();
	/**
	 * Spécifie le parent de l'objet.
	 * @param parent
	 */
	public void setParent(IGitObject parent);
}
