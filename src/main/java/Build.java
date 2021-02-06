import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.util.FileUtils;

/**
 * Contains information needed for the build and methods for building
 */
public class Build {
    /**
     * Constructs with properties about what (branch) to clone and build
     */
    public Build() {
        // Now its not empty anymore
    }

    /**
     * Clones the specified repository branch into a local folder to prepare testing
     */
    public void cloneRepo() {
        String repoUrl = "https://github.com/Atema/DD2480-CI.git";
        try {
            System.out.println("Cloning " + repoUrl + " into current folder");
            Git.cloneRepository().setURI(repoUrl)
                    // .setDirectory(directory)
                    // .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                    .call();
            System.out.println("Completed Cloning");
        } catch (JGitInternalException e){
            System.out.println("Repo alreay cloned");
        }catch (GitAPIException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
        }
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
