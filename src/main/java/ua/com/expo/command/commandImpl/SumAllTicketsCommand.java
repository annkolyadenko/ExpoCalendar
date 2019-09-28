package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.IRequestValidator;
import ua.com.expo.util.validator.impl.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.Logger;

public class SumAllTicketsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SumAllTicketsCommand.class.getName());
    private final AdminService adminService;

    public SumAllTicketsCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestValidator validator = RequestValidator.getInstance();
        String idExpo = request.getParameter("expoId");
        if (validator.isNotNull(idExpo)) {
            Long expoId = Long.valueOf(idExpo);
            LOGGER.info("Expo id: " + expoId);
            Long amount = adminService.sumPurchasedTicketsByExpoId(expoId);
            Map<Expo, Long> tickets = adminService.sumAllPurchasedTickets();

            if (Objects.nonNull(amount)) {
                request.setAttribute("amount", amount);
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
            }
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Can't calculate tickets amount. Please, try again later");
    }
}
