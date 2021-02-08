import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet that handles requests (on /) to the web-interface for viewing build history
 */
public class BuildHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Configuration cfg;
    private final BuildDatabase db;

    public BuildHistoryServlet() {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(getClass(), "/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(false);

        db = new BuildDatabase();
    }

    /**
     * Handles the GET request for the build history pages
     *
     * @param req:  The incoming request
     * @param resp: The returning response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = cfg.getTemplate("history.ftlh");

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("buildList", db.getAllBuilds());

        try {
            int buildId = Integer.parseInt(req.getParameter("build"));

            dataModel.put("build", db.getBuild(buildId));
        } catch (NumberFormatException e) {}

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
