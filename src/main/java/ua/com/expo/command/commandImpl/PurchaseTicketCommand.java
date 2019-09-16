package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.Ticket;
import ua.com.expo.entity.User;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.TicketService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PurchaseTicketCommand implements Command {

    private TicketService ticketService;
    private static final Logger LOGGER = Logger.getLogger(PurchaseTicketCommand.class.getName());

    public PurchaseTicketCommand() {
        this.ticketService = ServiceFactory.getTicketService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        LOGGER.info("PurchaseTicketCommand started execute");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authorizedUser");
        Long userId = user.getId();
        Long expoId = Long.valueOf(request.getParameter("expoId"));
        Long ticketsAmount = Long.valueOf(request.getParameter("ticketsAmount"));
        Ticket ticket = ticketService.purchaseTicket(userId, expoId, ticketsAmount);
        request.setAttribute("ticket", ticket);
        LOGGER.info("ticket purchased");
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.purchase");
    }
}
