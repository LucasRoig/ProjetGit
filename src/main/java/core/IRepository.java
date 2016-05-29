package core;

import java.util.List;

import core.gitobjects.IGitObject;

public interface IRepository {
	/**
	 * Retourne la liste de tous les git objects présents de le repository
	 * @return la liste de tous les git objects présents de le repository
	 */
	public List<IGitObject> getObjectsList();
	
	/**
	 * Retourne le Git Object correspondant à la clef de hash passée en paramètre.
	 * @param hash de l'objet que l'on souhaite obtenir
	 * @return l'objet correspondant
	 */
	public IGitObject getObject(String hash);
}
