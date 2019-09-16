package ua.com.expo.command.commandImpl;

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
import java.util.logging.Logger;

public class GetAllShowroomCommand implements Command {

    private ShowroomService showroomService;
    private static final Logger LOGGER = Logger.getLogger(GetAllShowroomCommand.class.getName());

    public GetAllShowroomCommand() {
        this.showroomService = ServiceFactory.getShowroomService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        List<Showroom> showrooms = showroomService.findAllShowroom();
        System.out.println(showrooms.size());
        if (Objects.nonNull(showrooms) && !showrooms.isEmpty()) {
            request.setAttribute("showrooms", showrooms);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.showrooms");
        }
        //STUB!!!
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.shitHappens");
    }
}
