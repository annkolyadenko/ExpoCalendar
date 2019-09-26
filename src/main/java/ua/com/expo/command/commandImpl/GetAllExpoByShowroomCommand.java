package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.IRequestParametersValidator;
import ua.com.expo.util.validator.impl.RequestParameterValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GetAllExpoByShowroomCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(GetAllExpoByShowroomCommand.class.getName());
    private AdminService adminService;

    public GetAllExpoByShowroomCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestParametersValidator validator = RequestParameterValidatorImpl.getInstance();
        String idShowroom = request.getParameter("showroomId");
        if (validator.isNotNull(idShowroom) && validator.isNotEmpty(idShowroom)) {
            Long showroomId = Long.valueOf(idShowroom);
            List<Expo> expos = adminService.findAllExpoByShowroomId(showroomId);
            if (Objects.nonNull(expos) && !expos.isEmpty()) {
                request.setAttribute("expos", expos);
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
            }
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
