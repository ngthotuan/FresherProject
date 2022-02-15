package request;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostFormRequest extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PostFormRequest.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.warn("REQUEST POST: " + request.getRequestURI());
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password));
        logger.warn("RESPONSE POST: " + request.getRequestURI());
    }
}
