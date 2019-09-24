package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.Theme;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.ThemeService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class AddNewThemeCommand implements Command {

    private ThemeService themeService;
    private static final Logger LOGGER = Logger.getLogger(AddNewThemeCommand.class.getName());


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addTheme");
    }
}
