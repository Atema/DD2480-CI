/**
 * Contains information needed for the build and methods for building
 */
public class Build { 
    String branchRef;
    String id;
    String url;
    String nameAuthor;
    String emailAuthor;
    String timeStamp;

    /**
     * Constructs with properties about what (branch) to clone and build
     */
    public Build(String branchRef,String id,String url,String nameAuthor,
    String emailAuthor,String timeStamp) {
        this.branchRef = branchRef;
        this.id = id;
        this.url = url;
        this.nameAuthor = nameAuthor;
        this.emailAuthor = emailAuthor;
        this.timeStamp = timeStamp;

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
