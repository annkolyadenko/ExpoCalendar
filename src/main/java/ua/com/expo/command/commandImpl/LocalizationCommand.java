package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.UserDto;
import ua.com.expo.service.serviceImpl.UserService;
import ua.com.expo.util.resource.ConfigurationManager;

public class LocalizationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LocalizationCommand.class.getName());
    private UserService userService;

    public LocalizationCommand() {
        this.userService = Context.getInstance().getServiceFactory().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String ua = "uk_UA";
        String us = "en_US";
        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        String path = request.getParameter("path");
        LOGGER.info(path);
        if (language.equalsIgnoreCase("English")) {
            session.setAttribute("locale", us);
        } else if (language.equalsIgnoreCase("Ukrainian")) {
            session.setAttribute("locale", ua);
        }
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        if (Objects.nonNull(userDto)) {
            Long userId = userDto.getId();
            String locale = (String) session.getAttribute("locale");
            boolean isSaved = userService.saveLang(userId, locale);
            if (isSaved) {
                return path;
            } else {
                setErrorMessage(request);
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
            }
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.index");
    }

    private void setErrorMessage(HttpServletRequest request) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", "Internal error occurred. Language didn't changed. Please, try again later");
    }
}
