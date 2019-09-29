package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.IRequestValidator;
import ua.com.expo.util.validator.impl.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ApproveNewThemeCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ApproveNewThemeCommand.class.getName());
    private final AdminService adminService;


    public ApproveNewThemeCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestValidator validator = RequestValidator.getInstance();

        String theme = request.getParameter("theme");
        if (validator.isNotNull(theme) && validator.isNotEmpty(theme)) {
            //TODO
            boolean isSaved = adminService.saveTheme(theme);
            if (isSaved) {
                request.setAttribute("status", "New Theme Approved");
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addTheme");
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
