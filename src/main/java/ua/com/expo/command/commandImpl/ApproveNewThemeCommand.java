package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Theme;
import ua.com.expo.logic.ILogic;
import ua.com.expo.logic.LogicImpl;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.IRequestParametersValidator;
import ua.com.expo.util.validator.impl.RequestParameterValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ApproveNewThemeCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ApproveNewThemeCommand.class.getName());
    private final AdminService adminService;


    public ApproveNewThemeCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestParametersValidator validator = RequestParameterValidatorImpl.getInstance();
        ILogic logic = new LogicImpl();
        String theme = request.getParameter("theme");
        if (validator.isNotNull(theme) && validator.isNotEmpty(theme)) {
            List<Theme> themes = adminService.findAllThemes();
            //TODO
            if (!logic.ifExist(theme, themes)) {
                boolean isSaved = adminService.saveTheme(theme);
                if (isSaved)
                    request.setAttribute("status", "New Theme Approved");
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.approvedTheme");
            }
            request.setAttribute("status", "The theme you entered is already defined in the scope");
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addTheme");
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addTheme");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
