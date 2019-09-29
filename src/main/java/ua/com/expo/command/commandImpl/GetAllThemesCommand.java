package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.ThemeDto;
import ua.com.expo.entity.Theme;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GetAllThemesCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(GetAllThemesCommand.class.getName());
    private final AdminService adminService;


    public GetAllThemesCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<ThemeDto> list = adminService.findAllThemes();
        if (Objects.nonNull(list) && !list.isEmpty()) {
            request.setAttribute("themes", list);
            request.setAttribute("today", LocalDate.now());
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.themes");
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
    }
    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
