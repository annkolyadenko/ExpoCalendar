package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Logger;

public class GetStatsByExpoCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(GetStatsByExpoCommand.class.getName());
    private AdminService adminService;

    public GetStatsByExpoCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Expo, Long> tickets = adminService.sumAllPurchasedTickets();
        request.setAttribute("tickets", tickets);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.stats");
     }
}
