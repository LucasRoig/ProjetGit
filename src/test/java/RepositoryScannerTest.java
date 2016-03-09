import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import model.RepositoryScanner;

/**
 * 
 * @author Lucas Roig Classe de test pour RepositoryScanner Ici on vérifie
 *         simplement que le RepositoryScanner récupère la bonne liste de
 *         valeurs de hash.
 */
public class RepositoryScannerTest {

	@Test
	public void test() {
		File file = new File("src/test/ressources/objects");
		RepositoryScanner repositoryScanner = new RepositoryScanner(file);
		ArrayList<String> expectedList = new ArrayList<>();
		expectedList.add("5d2a619a227dd05e3151e6bedb1bed53cfd37a16");
		expectedList.add("b6e799b3a0c8b3614c0a955994a80a9ea50ea204");
		expectedList.add("d0e5aa177f2dc29ba5e8ef31dba60efdd0b24a3a");
		ArrayList<String> actual = new ArrayList<>();
		for (String string : repositoryScanner) {
			actual.add(string);
		}
		assertTrue("Test du contenu du RepositoryScanner", expectedList.equals(actual));
	}
}
