import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import netscape.javascript.JSObject;

/**
 * Contains the results of a build, and methods to report them to the user
 */
public class BuildResult {
    private final Build build;
    private final BuildStatus buildStatus;
    public String gitResponse;

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
     * Reports the build result as a status on GitHub, the status reponse from Git
     * is saved in gitResponse.
     * 
     */
    public <JSONobject> void reportGitHubStatus() {

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.post(build.statusURL.replace("{sha}", build.idSHA))
                    .header("Authorization", "Bearer " + EnvVars.getToken()) // bearer token to be imported, see line 35
                    .header("Content-Type", "application/json").body(buildStatus.toString()).asString();

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONobject responseJSON = new JSONObject(response);
        gitResponse = responseJSON.getString("status");

       
        


    }
}
