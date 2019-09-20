package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.User;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.UserService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.Validator;

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


public class SignUpCommand implements Command {
    private UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(SignUpCommand.class.getName());

    public SignUpCommand() {
        userService = ServiceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        HttpSession session = request.getSession();
        String name = request.getParameter("userName");
        String email = request.getParameter("email");
        String language = (String) session.getAttribute("locale");
        LOGGER.debug("LOCALE :" + language);
        String password = request.getParameter("password");
        if (Validator.requestParametersValidator(email, password)) {
            //TODO Optional
            User user = userService.findUserByEmail(email);
            if (Objects.isNull(user)) {
                user = userService.signUpUser(name, email, language, password);
                if (Objects.nonNull(user)) {
                    session.setAttribute("authorizedUser", user);
                    return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
                }
            }
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.index");
    }
}
