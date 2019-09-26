package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.UserDto;
import ua.com.expo.entity.Ticket;
import ua.com.expo.entity.User;
import ua.com.expo.service.serviceImpl.VisitorService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class GetAllTicketsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(GetAllTicketsCommand.class.getName());
    private final VisitorService visitorService;


    public GetAllTicketsCommand() {
        this.visitorService = Context.getInstance().getServiceFactory().getVisitorService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        if (Objects.nonNull(userDto)) {
            Long userId = userDto.getId();
            List<Ticket> tickets = visitorService.findAllTicketsByUserId(userId);
            if (Objects.nonNull(tickets) && !tickets.isEmpty()) {
                request.setAttribute("tickets", tickets);
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.tickets");
            }
            request.setAttribute("emptyList", "No tickets found");
            return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
        }
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
