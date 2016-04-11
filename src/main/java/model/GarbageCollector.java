package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Lucas Roig Implémentation d'un garbage collector. En partant du
 *         commit "HEAD" de chaque branche, le garbage collector progresse de
 *         parent en parent et indique que chaque objet trouvé ne doit pas être
 *         supprimé. Les objets qui n'ont pas été visités par cette procédure
 *         peuvent être supprimés sans risque. Les "HEAD" de chaque branches
 *         sont trouvés dans le dossier .git/refs/heads Le stash est lui dans le
 *         fichier .git/refs/stash
 */
public class GarbageCollector {
	RepositoryData repository;
	ArrayList<Commit> commitList = new ArrayList<>();

	public GarbageCollector(RepositoryData repository) throws IOException {
		this.repository = repository;
		File headFolder = new File(repository.getDirectory().getAbsolutePath() + "/.git/refs/heads");
		BufferedReader br = null;
		for (File headRef : headFolder.listFiles()) {
			br = new BufferedReader(new FileReader(headRef));
			String hash = br.readLine();
			commitList.add((Commit) repository.getObjectByHash(hash));
		}

		File stash = new File(repository.getDirectory().getAbsolutePath() + "/.git/refs/stash");
		if (stash.exists()) {
			br = new BufferedReader(new FileReader(stash));
			String line;
			while ((line = br.readLine()) != null) {
				commitList.add((Commit) repository.getObjectByHash(line));
			}
		}

		br.close();
	}

	/**
	 * Marque tous les objets trouvés par le garbage collector comme n'étant
	 * pas supprimables.
	 */
	public void markAllObjects() {
		for (Commit commit : commitList) {
			commit.setDeletable(false);
		}
	}
}
