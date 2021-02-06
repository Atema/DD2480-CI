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
    /**
     * Constructs with properties about what (branch) to clone and build
     */
    public Build() {
        // Now its not empty anymore
    }

    /**
     * Clones the specified repository branch into a local folder to prepare testing
     */
    public String cloneRepo() {
        String repoUrl = "https://github.com/Atema/DD2480-CI.git";
        try {
            Path p = Files.createTempDirectory("repo");
            System.out.println("Cloning " + repoUrl + " into " + p.toString());
            Git.cloneRepository().setURI(repoUrl)
                    .setDirectory(p.toFile())
                    // .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                    .call();
            System.out.println("Completed Cloning");
            return p.toString();
        } catch (JGitInternalException e){
            System.out.println("Repo alreay cloned");
        }catch (GitAPIException e) {
            System.out.println("Exception occurred while cloning repo");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("failded to create the folder");
        }
        return "";
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
