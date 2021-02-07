import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

enum GitMessages {
    SUCCESS,
    FAILURE,
    ERROR,
}


/**
 * Contains the results of a build, and methods to report them to the user
 */
public class BuildResult {
    private final Build build;
    private final GitMessages buildStatus;

    /**
     * Constructs with the results
     *
     * @param build The associated build
     */
    public BuildResult(Build build, GitMessages buildStatus) {
        this.build = build;
        this.buildStatus = buildStatus;

    }

    /**
     * Reports the build result as a status on GitHub,
     * each contributor is bound by their own bearerToken, registered through Github settings
     */
    public void reportGitHubStatus() {
        String sha = "toBeImplemented"; // sha is to be imported from build in the futre
        String bearerToken = "toBeImported"; // Each contributor is to create their own token which shall be imported from file
        JSONObject body = new JSONObject();

        switch (buildStatus) {
            case SUCCESS -> body.put("state", "success");
            case ERROR -> body.put("state", "error");
            case FAILURE -> body.put("state", "failure");
        }
        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.post("https://api.github.com/repos/Atema/DD2480-CI/statuses/" + sha)
                    .header("Authorization", "Bearer " + bearerToken) //bearer token to be imported, see line 35
                    .header("Content-Type", "application/json")
                    .body(body.toString())
                    .asString();

        } catch (UnirestException e) {
            e.printStackTrace();
        }


    }
}
