import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Contains information needed for the build and methods for building
 */
public class Build {
    String branchRef;
    String idSHA;
    String url;
    String nameAuthor;
    String emailAuthor;
    String timeStamp;
    String cloneURL;
    String statusURL;

    /**
     * Constructs with properties about what (branch) to clone and build
     *
     * @param branchRef reference for the branch
     * @param nameAuthor name of the author
     * @param emailAuthor email of the author
     * @param idSHA id of the SHA
     * @param url url
     * @param timeStamp time stamp
     * @param cloneURL url to clone the repo
     * @param statusURL url of the status
     *
     */
    public Build(String branchRef,String nameAuthor,String emailAuthor,String idSHA,String url,
    String timeStamp,String cloneURL, String statusURL) {
        this.branchRef = branchRef;
        this.idSHA = idSHA;
        this.url = url;
        this.nameAuthor = nameAuthor;
        this.emailAuthor = emailAuthor;
        this.timeStamp = timeStamp;
        this.cloneURL = cloneURL;
        this.statusURL = statusURL;
    }

    /**
     * Clones the specified repository branch into a local folder to prepare testing
     *
     * @throws GitAPIException if an error occured while cloning the repo
     * @throws JGitInternalException if the repo is already cloned for example
     * @throws IOException if the temp folder cannot be created
     *
     * @return The string corresponding to the path where the repo have been cloned
     *
     */
    public String cloneRepo() throws GitAPIException, JGitInternalException, IOException {
        String repoUrl = "https://github.com/Atema/DD2480-CI.git";
        Path p = Files.createTempDirectory("repo");
        System.out.println("Cloning " + repoUrl + " into " + p.toString());
        Git.cloneRepository().setURI(repoUrl)
                .setDirectory(p.toFile())
                // .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                .call();
        System.out.println("Completed Cloning");
        return p.toString();
    }

    /**
     * Runs the gradle build process (including tests)
     *
     * @throws GitAPIException throw by cloneRepo
     * @throws JGitInternalException throw by cloneRepo
     * @throws IOException throw by cloneRepo
     *
     * @return The results of the build
     */
    public BuildResult build() throws GitAPIException, JGitInternalException, IOException{
        cloneRepo();

        // ...

        return null;
    }
}
