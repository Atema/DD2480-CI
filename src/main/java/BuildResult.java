import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.JsonObject;
import com.mashape.unirest.http.Unirest;

import org.json.JSONObject;




/**
 * Contains the results of a build, and methods to report them to the user
 */
public class BuildResult {
    public Build build;
    public BuildStatus buildStatus;
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
    public void reportGitHubStatus() {

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<JsonNode> response = Unirest.post(build.statusURL+ build.idSHA )
                    .header("Authorization", "Bearer" + EnvVars.getToken())
                    .header("Content-Type", "application/json")
                    .body(buildStatus.toString())
                    .asJson();
                    JSONObject myObj = response.getBody().getObject();
                    //gitResponse = myObj.getString("state");
                    System.out.println(myObj.toString());
                    
                    

                  

        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }
}
