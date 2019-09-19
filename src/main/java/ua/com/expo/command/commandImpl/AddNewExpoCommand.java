package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.ShowroomService;
import ua.com.expo.service.serviceImpl.ThemeService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddNewExpoCommand implements Command {
    private ThemeService themeService;
    private ShowroomService showroomService;
    private static final Logger LOGGER = LogManager.getLogger(AddNewExpoCommand.class.getName());

    public AddNewExpoCommand() {
        this.themeService = ServiceFactory.getThemeService();
        this.showroomService = ServiceFactory.getShowroomService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        List<Theme> list = themeService.findAllThemes();
        List<Showroom> showrooms = showroomService.findAllShowroom();
        request.setAttribute("showrooms", showrooms);
        request.setAttribute("themes", list);
        request.setAttribute("today", LocalDate.now());
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addExpo");
    }
}
