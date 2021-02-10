import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * Contains information needed for the build and methods for building
 */
public class Build {
    String repoName;
    String branchRef;
    String idSHA;
    String url;
    String nameAuthor;
    String emailAuthor;
    long timeStamp;
    String cloneURL;
    String statusURL;
    String commitMessage;

    /**
     * Constructs with properties about what (branch) to clone and build
     *
     * @param branchRef   reference for the branch
     * @param nameAuthor  name of the author
     * @param emailAuthor email of the author
     * @param idSHA       id of the SHA
     * @param url         url of the commit
     * @param timeStamp   time stamp
     * @param cloneURL    url to clone the repo
     * @param statusURL   url of the status
     * @param commitMessage message of the commit
     *
     */
    public Build(String repoName, String branchRef, String nameAuthor, String emailAuthor, String idSHA, String url, long timeStamp,
            String cloneURL, String statusURL, String commitMessage) {
        this.repoName = repoName;
        this.branchRef = branchRef;
        this.idSHA = idSHA;
        this.url = url;
        this.nameAuthor = nameAuthor;
        this.emailAuthor = emailAuthor;
        this.timeStamp = timeStamp;
        this.cloneURL = cloneURL;
        this.statusURL = statusURL;
        this.commitMessage = commitMessage;
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
    public Path cloneRepo() throws GitAPIException, JGitInternalException, IOException {
        Path p = Files.createTempDirectory("repo");
        System.out.println("Cloning " + cloneURL + " into " + p.toString());

        CloneCommand command = Git.cloneRepository().setURI(cloneURL)
                .setDirectory(p.toFile());
        if (EnvVars.getToken() != null){
            command.setCredentialsProvider(new UsernamePasswordCredentialsProvider("token", EnvVars.getToken()));
        }
        command.call().checkout().setName(this.idSHA).call();
        System.out.println("Completed Cloning");
        return p;
    }

    /**
     * convert the input string into a string
     * @param stream to convert into string
     * @return the string version of stream
     */
    private String convertStreamToString(InputStream stream){
        Scanner sc = new Scanner(stream);
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()){
            sb.append(sc.nextLine());
            sb.append(System.getProperty("line.separator"));
        }
        sc.close();
        return sb.toString();
    }

    /**
     * Runs the gradle build process (including tests)
     *
     * @return The results of the build
     */
    public BuildResult build() {
        BuildResult result;
        Path buildDirectoryPath;

        try{
             buildDirectoryPath = this.cloneRepo();
        }catch(GitAPIException | JGitInternalException |IOException e){
            result = new BuildResult(this,BuildStatus.ERROR,e.getMessage());
            return result;
        }
        String operSys = System.getProperty("os.name").toLowerCase();
        ProcessBuilder p;

        if (operSys.contains("win")) {
                p = new ProcessBuilder("./gradlew.bat","build").directory(buildDirectoryPath.toFile());
        }else{
                p = new ProcessBuilder("./gradlew","build").directory(buildDirectoryPath.toFile());

        }
        p.redirectErrorStream(true);

        Process pr ;

        try{
             pr = p.start();
             pr.waitFor(); //wait for the process to finish

        }catch(InterruptedException | IOException e ){
            System.out.println(e.getMessage());
            result = new BuildResult(this,BuildStatus.ERROR,e.getMessage());
            return result;
        }

        InputStream outputBuild = pr.getInputStream();

        if(pr.exitValue() == 0 ){
            result = new BuildResult(this,BuildStatus.SUCCESS,convertStreamToString(outputBuild));

        }else{
            result = new BuildResult(this,BuildStatus.FAILURE,convertStreamToString(outputBuild));

        }

        return result;
    }
}
