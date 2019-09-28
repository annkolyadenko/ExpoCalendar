package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddNewExpoCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddNewExpoCommand.class.getName());
    private final AdminService adminService;

    public AddNewExpoCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Theme> list = adminService.findAllThemes();
        List<Showroom> showrooms = adminService.findAllShowroom();
        if (list != null && !list.isEmpty() && showrooms != null && !showrooms.isEmpty()) {
            request.setAttribute("showrooms", showrooms);
            request.setAttribute("themes", list);
            //TODO SEPARATE METHOD
            LocalDateTime dateTime = LocalDateTime.now();
            String today = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            request.setAttribute("today", today.substring(0, 16));
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.addExpo");
    }
}
