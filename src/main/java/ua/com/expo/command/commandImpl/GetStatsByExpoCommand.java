package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.ExpoDto;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.pagination.PaginationUtil;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetStatsByExpoCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetStatsByExpoCommand.class.getName());
    private AdminService adminService;

    public GetStatsByExpoCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String limitParameter = request.getParameter("limit");
        String currPage = request.getParameter("currentPage");
        Integer limit = Integer.parseInt(limitParameter);
        Integer currentPage = Integer.parseInt(currPage);
        Integer rows = adminService.findNumberOfRowsExpos();
        LOGGER.debug(rows + "ROWS");
        PaginationUtil.getInstance().doPagination(limit, currentPage, rows, request);
        LinkedHashMap<ExpoDto, Long> statistic = adminService.sumAllPurchasedTicketsPageable(limit, currentPage);
        request.setAttribute("statistic", statistic);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.stats");
    }
}
