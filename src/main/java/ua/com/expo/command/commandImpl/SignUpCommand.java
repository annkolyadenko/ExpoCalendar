package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.User;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.UserService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.validator.IRequestParametersValidator;
import ua.com.expo.util.validator.impl.RequestParameterValidatorImpl;


public class SignUpCommand implements Command {

    private final UserService userService;
    private final IRequestParametersValidator requestParametersValidator;
    private static final Logger LOGGER = LogManager.getLogger(SignUpCommand.class.getName());
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public SignUpCommand() {
        this.userService = serviceFactory.getUserService();
        this.requestParametersValidator = RequestParameterValidatorImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String name = request.getParameter("userName");
        String email = request.getParameter("email");
        String language = (String) session.getAttribute("locale");
        LOGGER.debug("LOCALE :" + language);
        String password = request.getParameter("password");
        if (requestParametersValidator.emailPasswordValidate(email, password)) {
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
