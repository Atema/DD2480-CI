import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link Build} class
 */
public class BuildTests {

	/**
	 * Test function for the method cloneRepo
	 *
	 * @result true if the the current repository have correctly be clone into a temp folder
	 */
	@DisplayName("clone repo test")
	@Test
	void cloneRepoTest(){
		Build b = new Build("", "", "", "", "", "", "", "");
		try {
			Path path = Paths.get(b.cloneRepo());
			assertTrue(Files.exists(path));
		} catch (GitAPIException e) {
			System.err.println("Exception occurred while cloning repo");
			e.printStackTrace();
			assertTrue(false);
		}catch (JGitInternalException e){
			System.err.println("Repo alreay cloned");
			assertTrue(false);
		} catch (IOException e){
			System.err.println("failded to create the folder");
			assertTrue(false);
		}
	}
}
