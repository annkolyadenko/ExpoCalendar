package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.ExpoService;
import ua.com.expo.service.serviceImpl.TicketService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class SumAllTicketsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SumAllTicketsCommand.class.getName());
    private TicketService ticketService;
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public SumAllTicketsCommand() {
        this.ticketService = serviceFactory.getTicketService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        Long expoId = Long.valueOf(request.getParameter("expoId"));
        LOGGER.info("Expo id: " + expoId);
        Long amount = ticketService.sumPurchasedTicketsByExpoId(expoId);
        if (Objects.nonNull(amount)) {
            request.setAttribute("amount", amount);
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
        }
        ///STUB
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.shitHappens");
    }
}
