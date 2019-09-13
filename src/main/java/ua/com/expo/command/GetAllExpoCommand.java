package ua.com.expo.command;

import ua.com.expo.entity.Expo;
import ua.com.expo.services.factory.ServiceFactory;
import ua.com.expo.services.servicesImpl.ExpoService;
import ua.com.expo.utils.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GetAllExpoCommand implements Command {
    private ExpoService expoService;
    private static final Logger LOGGER = Logger.getLogger(GetAllThemesCommand.class.getName());

    public GetAllExpoCommand() {
        this.expoService = ServiceFactory.getExpoService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        Long themeId = Long.parseLong(request.getParameter("themeId"));
        List<Expo> list = expoService.findAllExpoByThemeId(themeId);
        System.out.println(list);
        if (Objects.nonNull(list) && !list.isEmpty()) {
            request.setAttribute("expo", list);
            //ASK
            request.setAttribute("size", list.size());
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.calendar");
        }
        ///STUB
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.shitHappens");
    }
}
