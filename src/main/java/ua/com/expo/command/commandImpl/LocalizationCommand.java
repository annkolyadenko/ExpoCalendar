package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.resource.ConfigurationManager;

public class LocalizationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LocalizationCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        HttpSession session = request.getSession();
        String language = request.getParameter("language");

        if (language.equalsIgnoreCase("English")) {
            session.setAttribute("locale", "en_US");
        } else if(language.equalsIgnoreCase("Ukrainian")) {
            session.setAttribute("locale", "uk_UA");
        }
        LOGGER.debug("CURRENT LOCALE: " + session.getAttribute("locale"));
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
    }
}
