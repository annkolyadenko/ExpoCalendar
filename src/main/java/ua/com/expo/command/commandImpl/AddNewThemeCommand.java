package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class AddNewThemeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddNewThemeCommand.class.getName());


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addTheme");
    }
}
