package ua.com.expo.command.commandImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.ExpoDto;
import ua.com.expo.entity.Expo;
import ua.com.expo.service.serviceImpl.AdminService;
import ua.com.expo.util.pagination.PaginationUtil;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.IRequestValidator;
import ua.com.expo.util.validator.impl.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class GetAllExpoByShowroomCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(GetAllExpoByShowroomCommand.class.getName());
    private AdminService adminService;

    public GetAllExpoByShowroomCommand() {
        this.adminService = Context.getInstance().getServiceFactory().getAdminService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestValidator validator = RequestValidator.getInstance();
        HttpSession session = request.getSession();
        String idShowroom = (String) session.getAttribute("showroomId");
        String limitParameter = request.getParameter("limit");
        String currPage = request.getParameter("currentPage");
        Integer limit = Integer.parseInt(limitParameter);
        Integer currentPage = Integer.parseInt(currPage);

        if (validator.isNotNull(idShowroom) && validator.isNotEmpty(idShowroom)) {
            Long showroomId = Long.valueOf(idShowroom);
            LOGGER.debug("adminService.findNumberOfRowsExposByShowroomId(showroomId) :" + showroomId);
            Integer rows = adminService.findNumberOfRowsExposByShowroomId(showroomId);
            LOGGER.debug(rows + "ROWS");
            PaginationUtil.getInstance().doPagination(limit, currentPage, rows, request);
            List<ExpoDto> expos = adminService.findAllExpoByShowroomIdPageable(showroomId, limit, currentPage);
            if (Objects.nonNull(expos) && !expos.isEmpty()) {
                request.setAttribute("expos", expos);
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
            }
        }
        //TODO INFO IF EMPTY
        setErrorMessage(request);
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.expos");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Unable to complete your request. Please, try again later");
    }
}
