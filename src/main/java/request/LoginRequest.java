package request;

import db.MySQL;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginRequest extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginRequest.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.warn("REQUEST LOGIN: " + request.getRequestURI());
        String name = request.getParameter("name");

        boolean isValid = MySQL.login(name);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("{ \"valid\": " + isValid + " }");
        logger.warn("RESPONSE LOGIN: " + request.getRequestURI());
    }
}
