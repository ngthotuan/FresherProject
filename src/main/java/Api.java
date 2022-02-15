import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import request.GetRequest;
import request.LoginRequest;
import request.PostFormRequest;
import request.PostJsonRequest;

public class Api {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new GetRequest()), "/");
        context.addServlet(new ServletHolder(new PostJsonRequest()), "/json");
        context.addServlet(new ServletHolder(new PostFormRequest()), "/form");
        context.addServlet(new ServletHolder(new LoginRequest()), "/login");

        server.start();
        server.join();
    }
}