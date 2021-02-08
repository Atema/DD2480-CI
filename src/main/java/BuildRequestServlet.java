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
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        if(req.getMethod() == "POST"){
            String reqData = req.getReader().lines().collect(Collectors.joining());
            JSONObject reqObject = new JSONObject(reqData);
            String branchRef = reqObject.getString("ref");            
            JSONArray commitArray = reqObject.getJSONArray("commits");
            String idSHA = commitArray.getJSONObject(0).getString("id");
            String url = reqObject.getJSONObject("repository").getString("html_url");
            String nameAuthor = commitArray.getJSONObject(0).getJSONObject("author").getString("name");
            String emailAuthor = commitArray.getJSONObject(0).getJSONObject("author").getString("email");
            String timeStamp = commitArray.getJSONObject(0).getString("timestamp");

            Build build = New Build(branchRef,idSHA,url,nameAuthor,emailAuthor,timeStamp);

            build.build();


        }
    }
}
