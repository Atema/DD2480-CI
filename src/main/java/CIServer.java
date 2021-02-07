import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.eclipse.jetty.util.resource.Resource;

/**
 * Starts the HTTP server, and forwards requests to the right handlers (servlets)
 */
public class CIServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ResourceHandler staticHandler = new ResourceHandler();
        staticHandler.setBaseResource(Resource.newClassPathResource("/static"));

        ContextHandler staticCtxHandler = new ContextHandler("/static");
        staticCtxHandler.setHandler(staticHandler);


        ServletContextHandler servletHandler = new ServletContextHandler();
        server.setHandler(servletHandler);

        servletHandler.addServlet(new ServletHolder(BuildRequestServlet.class), "/request");
        servletHandler.addServlet(new ServletHolder(BuildHistoryServlet.class), "/");


        server.setHandler(new HandlerList(new Handler[] { staticCtxHandler, servletHandler, new DefaultHandler() }));

        server.start();
        server.join();
    }
}
