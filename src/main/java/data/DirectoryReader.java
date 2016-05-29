package data;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.internal.storage.file.PackFile;
import org.eclipse.jgit.internal.storage.file.PackIndex.MutableEntry;

/**
 * Classe contenant des méthodes statiques permettants de lire le répertoire object d'un projet git.
 *
 */
public class DirectoryReader {
	/**
	 * Retourne la liste des clefs de hash des objets git trouvés dans le répertoire f.
	 * @param f le répertoire dans lequel chercher les objets git.
	 * @return Une liste des clefs de hash des différents objets trouvés.
	 */
	public static List<String> readDirectory(File f){
		List<String> result = new ArrayList<>();
		FileFilter directoryNameFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory() && !pathname.getName().contains("pack")
						&& !pathname.getName().contains("info");
			}
		};
		FileFilter subFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		};
		
		//Lecture des objets "normaux"
		for (File subDirectory : f.listFiles(directoryNameFilter)) {
			for (File gitObject : subDirectory.listFiles(subFileFilter)) {
				result.add(subDirectory.getName() + gitObject.getName());
			}
		}
		
		//Lecture des packs
		File packDirectory = new File(f.getPath() + "/pack");
		FilenameFilter packFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".pack");
			}
		};
		for (File pack : packDirectory.listFiles(packFilter)) {
			PackFile p = new PackFile(pack, 0);// TODO : c'est quoi extension ?
			for (MutableEntry mutableEntry : p) {
				result.add(mutableEntry.toObjectId().getName());
			}
		}
		return result;
	}
}
