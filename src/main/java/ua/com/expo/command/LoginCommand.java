package ua.com.expo.command;

import ua.com.expo.entity.User;
import ua.com.expo.services.factory.ServiceFactory;
import ua.com.expo.services.servicesImpl.UserService;
import ua.com.expo.utils.resource.ConfigurationManager;
import ua.com.expo.utils.validator.Valid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;


public class LoginCommand implements Command {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getName());

    public LoginCommand() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //TO DO!!!
        if (Valid.requestParametersValidator(email, password)) {
            //TO DO!!! OPTIONAL
            User user = userService.findUserByEmail(email);
            try {
                if (Objects.nonNull(user) && Valid.passwordValidator(password, user)) {
                    //TO DO!!! isLogged check!!!
                    //TO DO!!! logIn add
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
