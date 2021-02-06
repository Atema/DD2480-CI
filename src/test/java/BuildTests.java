import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link Build} class
 */
public class BuildTests {
	@DisplayName("clone repo test")
	@Test
	void cloneRepoTest(){
		Build b = new Build();
		Path path = Paths.get(b.cloneRepo());
		assertTrue(Files.exists(path));
	}
}
