import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * Contains the results of a build, and methods to report them to the user
 */
public class BuildResult {
    private final Build build;
    private final BuildStatus buildStatus;
    private String gitResponse;

    /**
     * Constructs with the results
     *
     * @param build The associated build
     */
    public BuildResult(Build build, BuildStatus buildStatus) {
        this.build = build;
        this.buildStatus = buildStatus;
        this.gitResponse = null;
    }

    /**
     * Reports the build result as a status on GitHub,
     * @param URL and @param idSHA are from the belonging build class,
     * @param bearerToken is imported from environment variable,
     * @param buildstatus reports the success, failure or error of the build to Github API.
     * 
     */
    public void reportGitHubStatus() {
        }
        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.post(build.statusURL.replace("{sha}", build.idSHA))
                    .header("Authorization", "Bearer " + EnvVars.getToken()) //bearer token to be imported, see line 35
                    .header("Content-Type", "application/json")
                    .body(buildStatus.toString())
                    .asString();

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONobject responseJSON = new JSONobject(response);
        gitResponse = responseJSON.getString("status");

       
        


    }
}
