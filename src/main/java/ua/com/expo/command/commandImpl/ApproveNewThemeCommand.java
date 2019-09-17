package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.entity.Theme;
import ua.com.expo.logic.ILogic;
import ua.com.expo.logic.LogicImpl;
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


public class ApproveNewThemeCommand implements Command {

    private ThemeService themeService;
    private static final Logger LOGGER = LogManager.getLogger(ApproveNewThemeCommand.class.getName());
    private ILogic logic;

    public ApproveNewThemeCommand() {
        this.themeService = ServiceFactory.getThemeService();
        logic = new LogicImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        String theme = request.getParameter("theme");
        boolean result = false;
        List<Theme> themes = null;
        themes = themeService.findAllThemes();
        if (!logic.ifExist(theme, themes)){
            result = themeService.createTheme(theme);
            LOGGER.debug("Status: " + result);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.approvedTheme");
        }
        //TODO
        return null;
    }
}
