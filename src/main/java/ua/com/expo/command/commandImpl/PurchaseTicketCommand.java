package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.User;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.TicketService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PurchaseTicketCommand implements Command {

    private TicketService ticketService;
    private static final Logger LOGGER = LogManager.getLogger(PurchaseTicketCommand.class.getName());

    public PurchaseTicketCommand() {
        this.ticketService = ServiceFactory.getTicketService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("PurchaseTicketCommand started execute");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authorizedUser");
        Long userId = user.getId();
        Long expoId = Long.valueOf(request.getParameter("expoId"));
        LOGGER.info("Expo id: " + expoId);
        Long ticketsAmount = Long.valueOf(request.getParameter("ticketsAmount"));
        boolean result = ticketService.purchaseTicket(userId, expoId, ticketsAmount);
        LOGGER.debug("Purchase ticket status: "+ result);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.purchase");
    }
}
