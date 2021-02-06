import static org.junit.jupiter.api.Assertions.assertTrue;
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
		b.cloneRepo();
		try{
			b.cloneRepo();
		}
		catch (Exception e) {
            assertTrue(e.getMessage().contains("not an empty directory"));
		}
	}
}
