package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.serviceImpl.VisitorService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;
import ua.com.expo.util.validator.IRequestValidator;
import ua.com.expo.util.validator.impl.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class GetAllExpoByThemeCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetAllExpoByThemeCommand.class.getName());
    private final VisitorService visitorService;
    private TimeConverter timeConverter = TimeConverter.getInstance();

    public GetAllExpoByThemeCommand() {
        this.visitorService = Context.getInstance().getServiceFactory().getVisitorService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestValidator validator = RequestValidator.getInstance();
        String idTheme = request.getParameter("themeId");
        String chosenDate = request.getParameter("chosenDate");
        LOGGER.debug("CHOSEN_DATE" + chosenDate);
        if (validator.isNotNull(idTheme, chosenDate) && validator.isNotEmpty(idTheme, chosenDate)) {
            Long themeId = Long.parseLong(idTheme);
            List<Expo> list = visitorService.findAllExpoByThemeIdAndDate(themeId, timeConverter.convertStringDateToDatabase(chosenDate));
            if (Objects.nonNull(list) && !list.isEmpty()) {
                request.setAttribute("expos", list);
                request.setAttribute("chosenDate", chosenDate);
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.calendar");
            }
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");

    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
