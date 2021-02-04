/**
 * Contains the results of a build, and methods to report them to the user
 */
public class BuildResult {
    private final Build build;

    /**
     * Constructs with the results
     *
     * @param build The associated build
     */
    public BuildResult(Build build) {
        this.build = build;
    }

    /**
     * Reports the build result as a status on GitHub
     */
    public void reportGitHubStatus() {
    }
}
