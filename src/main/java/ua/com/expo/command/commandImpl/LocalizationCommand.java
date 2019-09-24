package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.User;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.UserService;
import ua.com.expo.util.resource.ConfigurationManager;

public class LocalizationCommand implements Command {
    private UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(LocalizationCommand.class.getName());

    public LocalizationCommand() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String ua = "uk_UA";
        String us = "en_US";
        boolean result = false;
        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        String path = request.getParameter("path");
        LOGGER.info(path);
        if (language.equalsIgnoreCase("English")) {
            session.setAttribute("locale", us);
        } else if (language.equalsIgnoreCase("Ukrainian")) {
            session.setAttribute("locale", ua);
            User user = (User) session.getAttribute("authorizedUser");
            LOGGER.debug(user);
            if (Objects.nonNull(user)) {
                Long userId = user.getId();
                result = userService.updateLang(userId, ua);
            }
            if (result) {
                //TODO REWRITE
                LOGGER.debug("CURRENT LOCALE: " + session.getAttribute("locale"));
                return path;
            }
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.shitHappens");
    }
}
