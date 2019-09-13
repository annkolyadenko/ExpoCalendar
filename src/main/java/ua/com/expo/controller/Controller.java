package ua.com.expo.controller;

import ua.com.expo.command.Command;
import ua.com.expo.command.client.CommandEnum;
import ua.com.expo.model.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;


public class Controller extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //how to put init parameters
        //TO DO!!!
        /*LOGGER.info("Test");*/
        ConnectionPool.getInstance();
    }

    //TO DO!!!
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = null;
        try {
            String s = req.getParameter("command");
            path = handler(req, resp);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    /**
     * Post/Redirect/Get (PRG) is a web development design pattern that prevents some duplicate form submissions,
     * creating a more intuitive interface for user agents (users).
     * When a web form is submitted to a server through an HTTP POST request, a web user that attempts to refresh the server
     * response in certain user agents can cause the contents of the original POST request to be resubmitted,
     * possibly causing undesired results, such as a duplicate web purchase.
     * To avoid this problem, many web developers use the PRG pattern—instead of returning a web page directly,
     * the POST operation returns a redirection command. The HTTP 1.1 specification introduced the HTTP 303 ("See other")
     * response code to ensure that in this situation, the web user's browser can safely refresh the server response
     * without causing the initial POST request to be resubmitted. However, most common commercial applications
     * in use today (new and old alike) still continue to issue HTTP 302 ("Found") responses in these situations.
     * The PRG pattern cannot address every scenario of duplicate form submission. For example,
     * if a web user refreshes before the initial submission has completed because of server lag,
     * a duplicate POST request will occur in certain user agents.
     */
    //TO DO!!!
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = null;
        try {
            path = handler(req, resp);
            resp.sendRedirect(req.getContextPath() + path);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        //TO DO!!!
        super.destroy();
    }

    //TO DO!!!
    private String handler(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ClassNotFoundException {
        String action = req.getParameter("command");
        Command command = CommandEnum.valueOf(action.toUpperCase()).getCommand();
        return command.execute(req, resp);
    }

}
