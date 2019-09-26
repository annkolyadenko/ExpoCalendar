package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.Theme;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.ThemeService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GetAllThemesCommand implements Command {

    private ThemeService themeService;
    private static final Logger LOGGER = Logger.getLogger(GetAllThemesCommand.class.getName());
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public GetAllThemesCommand() {
        this.themeService = serviceFactory.getThemeService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Theme> list = themeService.findAllThemes();
        if (Objects.nonNull(list) && !list.isEmpty()) {
            request.setAttribute("themes", list);
            request.setAttribute("today", LocalDate.now());
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.themes");
        }
        //STUB!!!
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.shitHappens");
    }
}
