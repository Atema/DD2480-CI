/**
 * Contains the results of a build, and methods to report them to the user
 */
public class BuildResult {
    private final Build build;
    final BuildStatus buildStatus;
    String buildMessage;


    /**
     * Constructs with the results
     *
     * @param build The associated build
     */
    public BuildResult(Build build, BuildStatus buildStatus, String buildMessage) {
        this.build = build;
        this.buildStatus = buildStatus;
        this.buildMessage = buildMessage;

    }

    /**
     * Reports the build result as a status on GitHub
     */
    public void reportGitHubStatus() {
    }
}
