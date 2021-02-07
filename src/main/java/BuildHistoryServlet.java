import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BuildHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Configuration cfg;

    public BuildHistoryServlet() {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(getClass(), "/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(false);
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

        int buildId;

        try {
            buildId = Integer.parseInt(req.getParameter("build"));
        } catch (NumberFormatException e) {
            buildId = -1;
        }

        if (buildId != -1) {
            dataModel.put("buildSelected", true);
            dataModel.put("id", buildId);
            dataModel.put("repo", "Atema/DD2480-CI");
            dataModel.put("branch", "main");
            dataModel.put("log", "BLALALABLABLABLABLA");
        } else {
            dataModel.put("buildSelected", false);
        }

        List<Object> buildList = new ArrayList<>();
        dataModel.put("buildList", buildList);

        Map<String, Object> build1 = new HashMap<>();
        build1.put("id", 1);
        build1.put("repo", "Atema/DD2480-CI");
        build1.put("branch", "main");
        build1.put("time", "07/02/2021, 17:12");

        Map<String, Object> build2 = new HashMap<>();
        build2.put("id", 2);
        build2.put("repo", "Atema/DD2480-CI");
        build2.put("branch", "main");
        build2.put("time", "07/02/2021, 17:16");

        buildList.add(build1);
        buildList.add(build2);

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
