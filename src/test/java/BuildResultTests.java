import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for the {@link BuildResult} class, 
 * In order Failure, Success, Error,
 * The tests can only truly be checked by checking the GitPage.
 */
@DisplayName("BuildResultTest Failure")
@Test
void calculateBuildResultFailureTest(){
    Build build =  new Build("", "", "", "", "", "", "", "");
    BuildResult buildResult = new BuildResult(build, BuildStatus.FAILURE);
    buildResult.reportGitHubStatus();
}
@DisplayName("BuildResultTest Success")
@Test
void calculateBuildResultFailureTest(){
    Build build =  new Build("", "", "", "", "", "", "", "");
    BuildResult buildResult = new BuildResult(build, BuildStatus.SUCCESS);
    buildResult.reportGitHubStatus();
}
@DisplayName("BuildResultTest Error")
@Test
void calculateBuildResultFailureTest(){
    Build build =  new Build("", "", "", "", "", "", "", "");
    BuildResult buildResult = new BuildResult(build, buildStatus.ERROR);
    buildResult.reportGitHubStatus();
}
