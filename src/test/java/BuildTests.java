import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

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
		Build b = new Build("", "", "", "", "a87ca7f2773847a5bf16bd466b1cb3d25af8a10a", "", 0, "https://github.com/Atema/DD2480-CI.git", "");
		try {
			Path path = b.cloneRepo();
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
	/**
	 * Test function for the method build with a commit known to be successful when built
	 *
	 * @result true if the build is successful
	 */
	@DisplayName("True Build Test")
	@Test
	void buildTrueTest(){
		Build b = new Build("", "", "", "", "b87cd13a41e84af77b2d77f8e167b35e5bd8771d", "", 0, "https://github.com/Atema/DD2480-CI.git", "");
		BuildResult buildRes = b.build();
		assertEquals(BuildStatus.SUCCESS, buildRes.buildStatus);
        assertNotEquals("", buildRes.buildMessage);
	}


	/**
	 * Test function for the method build with a commit known to be unsuccessful when built
	 *
	 * @result true if the build is unsuccesful
	 */
	@DisplayName("False Build Test")
	@Test
	void buildFalseTest(){
		Build b = new Build("", "", "", "", "a87ca7f2773847a5bf16bd466b1cb3d25af8a10a", "", 0, "https://github.com/Atema/DD2480-CI.git", "");
		BuildResult buildRes = b.build();
		assertEquals(BuildStatus.FAILURE, buildRes.buildStatus);
        assertNotEquals("", buildRes.buildMessage);

	}
}
