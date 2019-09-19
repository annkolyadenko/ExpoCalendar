package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.ExpoService;
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

public class GetAllExpoByShowroomCommand implements Command {

    private ExpoService expoService;
    private static final Logger LOGGER = Logger.getLogger(GetAllExpoByShowroomCommand.class.getName());

    public GetAllExpoByShowroomCommand() {
        this.expoService = ServiceFactory.getExpoService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        Long showroomId = Long.valueOf(request.getParameter("showroomId"));
        List<Expo> expos = expoService.findAllExpoByShowroomId(showroomId);
        if (Objects.nonNull(expos) && !expos.isEmpty()) {
            request.setAttribute("expos", expos);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
        }
        //STUB!!!
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.shitHappens");
    }
}