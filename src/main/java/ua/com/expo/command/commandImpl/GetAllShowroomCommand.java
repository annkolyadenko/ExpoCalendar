package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.ShowroomDto;
import ua.com.expo.entity.Showroom;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class GetAllShowroomCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(GetAllShowroomCommand.class.getName());
    private final AdminService adminService;


    public GetAllShowroomCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<ShowroomDto> showrooms = adminService.findAllShowroom();
        if (Objects.nonNull(showrooms) && !showrooms.isEmpty()) {
            request.setAttribute("showrooms", showrooms);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.showrooms");
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
