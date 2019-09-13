package ua.com.expo.command;

import ua.com.expo.entity.Theme;
import ua.com.expo.services.factory.ServiceFactory;
import ua.com.expo.services.servicesImpl.ThemeService;
import ua.com.expo.utils.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GetAllThemesCommand implements Command {
    private ThemeService themeService;
    private static final Logger LOGGER = Logger.getLogger(GetAllThemesCommand.class.getName());

    public GetAllThemesCommand() {
        this.themeService = ServiceFactory.getThemeService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        List<Theme> list = themeService.findAllThemes();
        System.out.println(list);
        if (Objects.nonNull(list) && !list.isEmpty()) {
            request.setAttribute("themes", list);
            //ASK
            request.setAttribute("size", list.size());
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.themes");
        }
        //STUB!!!
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.shitHappens");
    }
}
