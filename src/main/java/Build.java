/**
 * Contains information needed for the build and methods for building
 */
public class Build {
    /**
     * Constructs with properties about what (branch) to clone and build
     */
    public Build() {
    }

    /**
     * Clones the specified repository branch into a local folder to prepare testing
     */
    private void cloneRepo() {
    }

    /**
     * Runs the gradle build process (including tests)
     *
     * @return The results of the build
     */
    public BuildResult build() {
        cloneRepo();

        // ...

        return null;
    }
}
