import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Starts the HTTP server, and forwards requests to the right handlers (servlets)
 */
public class CIServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(new ServletHolder(BuildRequestServlet.class), "/request");

        server.start();
        server.join();
    }
}
