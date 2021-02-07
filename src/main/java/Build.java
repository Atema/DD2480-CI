import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder;

/**
 * Contains information needed for the build and methods for building
 */
public class Build {
    final String ref;
    final String pusherName;
    final String pusherMail;
    final String commitSHA;
    final String repoURL;
    String date;
    /**
     * Constructs with properties about what (branch) to clone and build
     */
    public Build(String ref, String pusherName, String pusherMail, String commitSHA, String repoURL, String date) {
        this.ref = ref;
        this.pusherName = pusherName;
        this.pusherMail = pusherMail;
        this.commitSHA = commitSHA;
        this.repoURL = repoURL;
        this.date = date;
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
     * @throws IOException
     * @throws InterruptedException
     */
    public BuildResult build(Build b) throws IOException, InterruptedException {
        Process p = new ProcessBuilder("./gradlew","check").directory(new File("repo")).start();
        p.waitFor();
        
        BuildResult result;
        if(p.exitValue() == 0 ){
             result = new BuildResult(b,GitMessages.SUCCESS);
            
        }else{
            result = new BuildResult(b,GitMessages.FAILURE);

        }

        

        return null;
    }
}
