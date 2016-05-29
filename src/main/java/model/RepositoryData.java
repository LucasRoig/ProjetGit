package model;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

import data.DirectoryReader;

/**
 * Stock les données du repository
 * 
 * @author Bruce Baumann
 * @author Ulysse Baumann
 * @author Lucas Roig
 *
 */
public class RepositoryData {

	private Repository repository;
	private File directory;
	private Hashtable<String, GitObject> objectTable = new Hashtable<>();

	/**
	 * Instancie un objet RepositoryData et récupere les données des objets git
	 * 
	 * @param path
	 *            Le chemin du repertoire objects
	 * @throws IOException
	 */
	public RepositoryData(String path) throws IOException {
		this(new File(path));
	}

	public RepositoryData(File directory) throws IOException {
		this.directory = directory;
		RepositoryBuilder builder = new RepositoryBuilder();
		builder.setMustExist(true);
		builder.setGitDir(new File(directory.getAbsolutePath() + "/.git"));
		this.repository = builder.build();

		List<String> hashList = DirectoryReader.readDirectory(new File(directory.getAbsolutePath() + "/.git/objects"));
		for (String hash : hashList) {
			GitObject object = GitObject.createGitObject(this, hash);
			objectTable.put(hash, object);
		}
		for (GitObject object : this.getObjectList()) {
			object.setDataContent();
		}

		GarbageCollector gc = new GarbageCollector(this);
		gc.markAllObjects();
	}

	/**
	 * Retourne un gitObject grâce à sa valeur de hash
	 * 
	 * @param hash
	 * @return Le git object dont la clé est la valeur hash
	 */
	public GitObject getObjectByHash(String hash) {
		return objectTable.get(hash);
	}

	/**
	 * Retourne la liste de tout les objets présent dans le répertoire objects
	 * 
	 * @return
	 * @return ArrayList des objets
	 */
	public Collection<GitObject> getObjectList() {
		return objectTable.values();
	}

	/**
	 * Retourne l'objet Repository de Jgit auquel correspond ce RepositoryData
	 * 
	 * @return
	 */
	public Repository getRepository() {
		return repository;
	}

	public File getDirectory() {
		return directory;
	}
}