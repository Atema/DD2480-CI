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
    void reportGitHubStatusFailureTest() {
        Build build = new Build("", "", "", "fbe20048d0cf2eb2aa28dcd654a33e877c4cd01d", "", "", "", "https://api.github.com/repos/Atema/DD2480-CI/statuses/{sha}");
        BuildResult buildResult = new BuildResult(build, BuildStatus.FAILURE, "");
        String response = buildResult.reportGitHubStatus();
        assertEquals(response, "failure");

    }

    @DisplayName("BuildResultTest Success")
    @Test
    void reportGitHubStatusSuccessTest() {
        Build build = new Build("", "", "", "fbe20048d0cf2eb2aa28dcd654a33e877c4cd01d", "", "", "", "https://api.github.com/repos/Atema/DD2480-CI/statuses/{sha}");
        BuildResult buildResult = new BuildResult(build, BuildStatus.SUCCESS, "");
        String response = buildResult.reportGitHubStatus();
        assertEquals(response, "success");
    }

    @DisplayName("BuildResultTest Error")
    @Test
    void reportGitHubStatusErrorTest() {
        Build build = new Build("", "", "", "fbe20048d0cf2eb2aa28dcd654a33e877c4cd01d", "", "", "", "https://api.github.com/repos/Atema/DD2480-CI/statuses/{sha}");
        BuildResult buildResult = new BuildResult(build, BuildStatus.ERROR, "");
        String response = buildResult.reportGitHubStatus();
        assertEquals(response, "error");
    }
}
