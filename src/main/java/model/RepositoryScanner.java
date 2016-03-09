package model;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jgit.internal.storage.file.PackFile;
import org.eclipse.jgit.internal.storage.file.PackIndex.MutableEntry;

/**
 * Scanne un dossier object généré par git et récupère les hash de chaque objet git
 * @author Bruce Baumann
 * @author Ulysse Baumann
 * @author Lucas Roig
 */
public class RepositoryScanner implements Iterable<String>{
	private String path;
	private ArrayList<String> hashList = new ArrayList<>();
	
	/**
	 * Instancie un objet RepositoryScanner et initialise la variable path
	 * @param path  Le chemin de du répertoire objects à scanner
	 */
	public RepositoryScanner(String path) {
		this(new File(path));
	}
	
	public RepositoryScanner(File file) {
		this.path = file.getAbsolutePath();
		this.scan();
	}
	
	@Override
	public Iterator<String> iterator() {
		return hashList.iterator();
	}
	
	/**
	 * Scanne le dossier ciblé par la variable path et stocke les hashs obtenus dans la liste hashList
	 */
	private void scan(){
		File repertoireObjet = new File(path);
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory() && !pathname.getName().contains("pack") && !pathname.getName().contains("info");
			}
		};
		for (File sousDossier : repertoireObjet.listFiles(filter)) {
			scanSubDirectory(sousDossier);
		}
		
		readPackFiles();
	}
	
	/**
	 * Scanne un dossier et ajoute les noms des fichiers trouvés à la liste hashList.
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
	
	/**
	 * Ajoute les hash des GitObjects �tants dans des packs � la liste hashList.
	 */
	private void readPackFiles(){
		File repertoirePack = new File(path+"/pack");
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".pack");
			}
		};
		for (File pack : repertoirePack.listFiles(filter)) {
			if(pack.getAbsolutePath().contains(".pack")){
				PackFile p = new PackFile(pack, 0);
				for (MutableEntry mutableEntry : p) {
					hashList.add(mutableEntry.toObjectId().getName());
				}
			}
		}
	}
}
