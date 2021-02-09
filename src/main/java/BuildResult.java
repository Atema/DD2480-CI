import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.JsonNode;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Contains the results of a build, and methods to report them to the user
 */
public class BuildResult {
    private final Build build;
    final BuildStatus buildStatus;
    String buildMessage;

    /**
     * Constructs with the results
     *
     * @param build The associated build
     * @param buildStatus The status of the finished build
     * @param buildMessage The log of the finished build
     */
    public BuildResult(Build build, BuildStatus buildStatus, String buildMessage) {
        this.build = build;
        this.buildStatus = buildStatus;
        this.buildMessage = buildMessage;
    }

    /**
     * Reports the build result as a status on GitHub, the status reponse from Git
     * is saved in gitResponse.
     *
     * @return Updated status as reported by GitHub
     */
    public String reportGitHubStatus() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("context", "DD2480 Gr. 7 CI Server");
        requestBody.put("state", buildStatus.toString());

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<JsonNode> response = Unirest.post(build.statusURL.replace("{sha}", build.idSHA))
                    .header("Authorization", "Bearer " + EnvVars.getToken())
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .asJson();

            System.out.println(response.getBody().getObject().toString());
            return response.getBody().getObject().getString("state");

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
