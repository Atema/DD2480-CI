import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link BuildResult} class, In order Failure, Success,
 * Error, The tests can only truly be checked by checking the GitPage.
 */

public class BuildResultTests {
    @DisplayName("BuildResultTest Failure")
    @Test
    void calculateBuildResultFailureTest() {
        Build build = new Build("", "", "", "", "fbe20048d0cf2eb2aa28dcd654a33e877c4cd01d", "", "", "https://api.github.com/repos/Atema/DD2480-CI/statuses/");
        BuildResult buildResult = new BuildResult(build, BuildStatus.FAILURE);
        buildResult.reportGitHubStatus();
        assertEquals(buildResult.gitResponse, "failure");

    }

    @DisplayName("BuildResultTest Success")
    @Test
    void calculateBuildResultSuccessTest() {
        Build build = new Build("", "", "", "", "fbe20048d0cf2eb2aa28dcd654a33e877c4cd01d", "", "", "https://api.github.com/repos/Atema/DD2480-CI/statuses/");
        BuildResult buildResult = new BuildResult(build, BuildStatus.SUCCESS);
        buildResult.reportGitHubStatus();
        assertEquals(buildResult.gitResponse, "success");
    }

    @DisplayName("BuildResultTest Error")
    @Test
    void calculateBuildResultErrorTest() {
        Build build = new Build("", "", "", "fbe20048d0cf2eb2aa28dcd654a33e877c4cd01d", "", "", "", "https://api.github.com/repos/Atema/DD2480-CI/statuses/");
        BuildResult buildResult = new BuildResult(build, BuildStatus.ERROR);
        buildResult.reportGitHubStatus();
        assertEquals(buildResult.gitResponse, "failure"); // both error and failure return failure @github API
    }
}
