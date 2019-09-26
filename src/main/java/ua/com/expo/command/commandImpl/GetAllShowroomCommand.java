package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.entity.Showroom;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.ShowroomService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class GetAllShowroomCommand implements Command {

    private ShowroomService showroomService;
    private static final Logger LOGGER = LogManager.getLogger(GetAllShowroomCommand.class.getName());

    public GetAllShowroomCommand() {
        this.showroomService = ServiceFactory.getShowroomService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Showroom> showrooms = showroomService.findAllShowroom();
        LOGGER.debug(showrooms.size());
        if (!showrooms.isEmpty()) {
            request.setAttribute("showrooms", showrooms);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.showrooms");
        }
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "No showrooms found");
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
    }
}
