package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.ExpoService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class ApproveNewExpoCommand implements Command {

    private ExpoService expoService;
    private static final Logger LOGGER = LogManager.getLogger(ApproveNewExpoCommand.class.getName());
    TimeConverter timeConverter = new TimeConverter();

    public ApproveNewExpoCommand() {
        this.expoService = ServiceFactory.getExpoService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        boolean result = false;
        Long showroomId = Long.valueOf(request.getParameter("showroomId"));
        LOGGER.debug(showroomId);
        Long themeId = Long.valueOf(request.getParameter("themeId"));
        LOGGER.debug(themeId);
        String chosenDate = request.getParameter("chosenDate");
        LOGGER.debug(chosenDate);
        Long price = Long.valueOf(request.getParameter("price"));
        LOGGER.debug(price);
        String info = request.getParameter("info");
        LOGGER.debug(info);
        List<Expo> expos = expoService.findAllExpoByShowroomIdAndDate(showroomId, timeConverter.convertStringDateTimeToDatabase(chosenDate));
        LOGGER.debug(expos);
        if (expos.isEmpty()) {
            result = expoService.createExpo(showroomId, themeId, chosenDate, price, info);
            LOGGER.debug(result);
        }
        if (result) {
            LOGGER.debug(result);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.approvedExpo");
        }
        //TODO
        return null;
    }
}
