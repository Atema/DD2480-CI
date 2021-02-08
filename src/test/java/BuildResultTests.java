import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link BuildResult} class
 */
@DisplayName("BuildResultTest Failure")
@Test
void calculateBuildResultFailureTest(){
    BuildResult buildResult = new BuildResult(null, GitMessages.FAILURE);
    buildResult.reportGitHubStatus();
}
@DisplayName("BuildResultTest Success")
@Test
void calculateBuildResultFailureTest(){
    BuildResult buildResult = new BuildResult(null, GitMessages.SUCCESS);
    buildResult.reportGitHubStatus();
}
@DisplayName("BuildResultTest Error")
@Test
void calculateBuildResultFailureTest(){
    BuildResult buildResult = new BuildResult(null, GitMessages.ERROR);
    buildResult.reportGitHubStatus();
}
