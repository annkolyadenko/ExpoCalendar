package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToStatsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.stats");
    }
}
