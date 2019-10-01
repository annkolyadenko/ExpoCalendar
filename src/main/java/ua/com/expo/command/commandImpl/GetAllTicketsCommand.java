package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.TicketDto;
import ua.com.expo.dto.UserDto;
import ua.com.expo.entity.Ticket;
import ua.com.expo.service.serviceImpl.VisitorService;
import ua.com.expo.util.pagination.PaginationUtil;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GetAllTicketsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(GetAllTicketsCommand.class.getName());
    private final VisitorService visitorService;


    public GetAllTicketsCommand() {
        this.visitorService = Context.getInstance().getServiceFactory().getVisitorService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //TODO
        Map<String, String> requestParameters = Command.extractParameters(request);
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        String limitParameter = request.getParameter("limit");
        String currPage = request.getParameter("currentPage");
        Integer limit = Integer.parseInt(limitParameter);
        Integer currentPage = Integer.parseInt(currPage);

        if (Objects.nonNull(userDto)) {
            Long userId = userDto.getId();
            Integer rows = visitorService.findNumberOfRowsTicketsByUserId(userId);
            LOGGER.debug(rows + "ROWS");
            PaginationUtil.getInstance().doPagination(limit, currentPage, rows, request);
            List<Ticket> tickets = visitorService.findAllTicketsByUserIdPageable(userId, limit, currentPage);
            LOGGER.debug("Pageable :" + tickets);
            if (Objects.nonNull(tickets) && !tickets.isEmpty()) {
                request.setAttribute("tickets", tickets);
                LOGGER.debug(tickets);
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.page");
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
