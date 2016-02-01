package model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Scanne un dossier object généré par git et récupère les hash de chaque objet git
 * @author Bruce Baumann
 * @author Ulysse Baumann
 * @author Lucas Roig
 *
 */
public class RepositoryScanner implements Iterable<String>{
	String path;
	ArrayList<String> hashList = new ArrayList<>();
	
	/**
	 * Instancie un objet RepositoryScanner et initialise la variable path
	 * @param path  Le chemin de du répertoire objects à scanner
	 */
	public RepositoryScanner(String path) {
		this.path = path;
		this.scan();
	}
	
	
	/**
	 * Retourne la valeur actuelle de path
	 * @return path
	 */
	public String getPath() {
		return this.path;
	}
	
	
	/**
	 * Scanne le dossier ciblé par la variable path et stocke les hashs obtenus dans la liste hashList
	 */
	private void scan(){
		File repertoireObjet = new File(path);
		FileFilter filter = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory() && pathname.getName() != "info" && pathname.getName() != "pack";
			}
		};
		
		for (File sousDossier : repertoireObjet.listFiles(filter)) {
			scanSubDirectory(sousDossier);
		}
	}
	
	/**
	 * Scanne un dossier et ajoute les noms des fichiers trouvés à la liste hashList
	 * @param directory Le dossier à scanner
	 */
	private void scanSubDirectory(File directory) {
		FileFilter filter = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		};
		
		for (File gitObject : directory.listFiles(filter)) {
			hashList.add(directory.getName() + gitObject.getName());
		}
	}

	@Override
	public Iterator<String> iterator() {
		return hashList.iterator();
	}
}
