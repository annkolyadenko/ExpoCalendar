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


public class SignInCommand implements Command {
    private UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class.getName());

    public SignInCommand() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LOGGER.info("Log in command started executing");
        //TODO
        if (Validator.requestParametersValidator(email, password)) {
            //TODO Optional
            User user = userService.findUserByEmail(email);
            try {
                if (Objects.nonNull(user) && Validator.passwordValidator(password, user)) {
                    //TODO!!! isLogged check!!!
                    //TODO!!! logIn add
                    HttpSession session = request.getSession();
                    session.setAttribute("authorizedUser", user);
                    return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.login");
    }
}
