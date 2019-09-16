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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GetAllTicketsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(GetAllTicketsCommand.class.getName());
    private TicketService ticketService;

    public GetAllTicketsCommand() {
        this.ticketService = ServiceFactory.getTicketService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        HttpSession session = request.getSession();
        //TODO CHECK IF EXIST
        User user = (User) session.getAttribute("authorizedUser");
        Long userId = user.getId();
        List<Ticket> tickets = ticketService.findAllTicketsByUserId(userId);
        if (!tickets.isEmpty()) {
            request.setAttribute("tickets", tickets);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.tickets");
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
    }
}
