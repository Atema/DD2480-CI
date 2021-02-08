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
     * @return 
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
        String reqData,branchRef,idSHA,url,nameAuthor,emailAuthor,timeStamp;

        try{
            reqData = req.getReader().lines().collect(Collectors.joining());
            JSONObject reqObject = new JSONObject(reqData);
            branchRef = reqObject.getString("ref");  
            url = reqObject.getJSONObject("repository").getString("html_url");          
            JSONArray commitArray = reqObject.getJSONArray("commits");
            idSHA = commitArray.getJSONObject(0).getString("id");
            nameAuthor = commitArray.getJSONObject(0).getJSONObject("author").getString("name");
            emailAuthor = commitArray.getJSONObject(0).getJSONObject("author").getString("email");
            timeStamp = commitArray.getJSONObject(0).getString("timestamp");
        }catch (JSONException e) {
            System.err.println(e.getMessage());
            return;
        }
            
        Build build = new Build(branchRef,nameAuthor,emailAuthor,idSHA,url,timeStamp);

        build.build();

        
    }

}
