import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import org.json.*;

/**
 * Servlet that handles build requests (on /request) that are triggered by GitHub
 */
public class BuildRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the POST request containing the payload
     *
     * @param req: The incoming request
     * @param resp: The returning response
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Process the request payload and feed to Build
        if (!req.getMethod().equals("POST")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        try{
            String reqData = req.getReader().lines().collect(Collectors.joining());
            Build build = JsonParsing(reqData);
            BuildResult result = build.build();
            result.addToDatabase();
            result.reportGitHubStatus();
            System.out.println("Request received");
        }catch (JSONException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Helper method for Json parsing, interpretes the Json String and finds the relevant
     * data.
     *
     * @param JsonData String of Jsondata
     * @return Build object when successful otherwise throws error
     */
    public static Build JsonParsing(String JsonData) {
        try{
            JSONObject reqObject = new JSONObject(JsonData);
            String branchRef = reqObject.getString("ref");
            String nameAuthor = reqObject.getJSONObject("pusher").getString("name");
            String emailAuthor = reqObject.getJSONObject("pusher").getString("email");
            JSONObject repo = reqObject.getJSONObject("repository");
            String repoName = repo.getString("full_name");
            String cloneUrl = repo.getString("clone_url");
            String statusesUrl = repo.getString("statuses_url");
            long timeStamp = repo.getLong("pushed_at");
            String idSHA = reqObject.getJSONObject("head_commit").getString("id");
            String commitUrl = reqObject.getJSONObject("head_commit").getString("url");

            return new Build(repoName,branchRef,nameAuthor,emailAuthor,idSHA,commitUrl,timeStamp,cloneUrl,statusesUrl);
        }catch (JSONException e) {
            throw new JSONException(e);
        }

    }

}
