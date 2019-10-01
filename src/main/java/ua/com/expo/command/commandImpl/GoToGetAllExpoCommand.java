package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoToGetAllExpoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("showroomId", request.getParameter("showroomId"));
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
    }
}
