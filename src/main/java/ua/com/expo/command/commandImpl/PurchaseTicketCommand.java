package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.UserDto;
import ua.com.expo.service.serviceImpl.VisitorService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.validator.IRequestParametersValidator;
import ua.com.expo.util.validator.impl.RequestParameterValidatorImpl;

import java.util.Objects;

public class PurchaseTicketCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PurchaseTicketCommand.class.getName());
    private final VisitorService visitorService;

    public PurchaseTicketCommand() {
        this.visitorService = Context.getInstance().getServiceFactory().getVisitorService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestParametersValidator validator = RequestParameterValidatorImpl.getInstance();
        LOGGER.info("PurchaseTicketCommand started execute");
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        Long userId = userDto.getId();
        String idExpo = request.getParameter("expoId");
        String amountTickets = request.getParameter("ticketsAmount");
        if (Objects.nonNull(userId) && validator.isNotNull(idExpo, amountTickets)) {
            Long expoId = Long.valueOf(idExpo);
            Long ticketsAmount = Long.valueOf(amountTickets);
            boolean isPurchased = visitorService.purchaseTicket(userId, expoId, ticketsAmount);
            if (isPurchased)
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.purchase");
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.error");
    }
}
