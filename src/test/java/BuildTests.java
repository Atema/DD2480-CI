import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

		try {
			Path path = Paths.get(Build.cloneRepo());
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
	 * Test function for the method build
	 * 
	 * @result true if the build is succesful 
	 */
	@DisplayName("True Build Test")
	@Test
	void buildTrueTest(){
		Build b = new Build("", "", "", "", "", "", "", "");
		BuildResult buildRes = Build.build(b);
		assertEquals(BuildStatus.SUCCESS, buildRes.buildStatus);
        assertEquals("", buildRes.buildMessage);
	}


	/**
	 * Test function for the method build
	 * 
	 * @result true if the build is unsuccesful 
	 */
	@DisplayName("False Build Test")
	@Test
	void buildFalseTest(){
		Build b = new Build("", "", "", "", "", "", "", "");
		BuildResult buildRes = Build.build(b);
		assertEquals(BuildStatus.FAILURE, buildRes.buildStatus);
        assertNotEquals("", buildRes.buildMessage);

	}
}
