import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import jakarta.servlet.ServletException;

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
    }
}
