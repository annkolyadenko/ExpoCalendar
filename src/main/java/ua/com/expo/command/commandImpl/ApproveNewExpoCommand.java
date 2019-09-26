package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;
import ua.com.expo.util.validator.IRequestParametersValidator;
import ua.com.expo.util.validator.impl.RequestParameterValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApproveNewExpoCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ApproveNewExpoCommand.class.getName());
    private final AdminService adminService;

    public ApproveNewExpoCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestParametersValidator validator = RequestParameterValidatorImpl.getInstance();
        TimeConverter timeConverter = new TimeConverter();
        //TODO VARARGS
        String idShowroom = request.getParameter("showroomId");
        String idTheme = request.getParameter("themeId");
        String chosenDate = request.getParameter("chosenDate");
        String expoPrice = request.getParameter("price");
        String info = request.getParameter("info");
        if (validator.isNotNull(idShowroom, idTheme, chosenDate, expoPrice, info) && validator.isNotEmpty(idShowroom, idTheme, chosenDate, expoPrice, info)) {
            Long showroomId = Long.valueOf(idShowroom);
            Long themeId = Long.valueOf(idTheme);
            Long price = Long.valueOf(expoPrice);
            List<Expo> expos = adminService.findAllExpoByShowroomIdAndDate(showroomId, timeConverter.convertStringDateTimeToDatabase(chosenDate));
            if (expos.isEmpty()) {
                boolean isSaved = adminService.saveExpo(showroomId, themeId, chosenDate, price, info);
                if (isSaved) {
                    return ConfigurationManager.PATH_MANAGER.getProperty("path.page.approvedExpo");
                }
            }
            request.setAttribute("status", "The expo you tried to enter is already defined in chosen showroom & date");
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addExpo");
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addExpo");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
