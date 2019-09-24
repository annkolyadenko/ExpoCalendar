package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.entity.User;
import ua.com.expo.exception_draft.RuntimeServiceException;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.service.serviceImpl.UserService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.IPasswordHashingValidator;
import ua.com.expo.util.validator.IRequestParametersValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.validator.impl.PasswordHashingValidatorImpl;
import ua.com.expo.util.validator.impl.RequestParameterValidatorImpl;


public class SignInCommand implements Command {
    private final UserService userService;
    private final IRequestParametersValidator requestParametersValidator;
    private final IPasswordHashingValidator passwordHashingValidator;
    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class.getName());

    public SignInCommand() {
        this.userService = ServiceFactory.getUserService();
        this.requestParametersValidator = RequestParameterValidatorImpl.getInstance();
        this.passwordHashingValidator = PasswordHashingValidatorImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (requestParametersValidator.emailPasswordValidate(email, password)) {
            try {
                User user = userService.findUserByEmail(email);
                if (Objects.nonNull(user) && passwordHashingValidator.passwordValidate(password, user)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authorizedUser", user);
                    session.setAttribute("locale", user.getLanguage());
                    LOGGER.debug("LOCALE: " + user.getLanguage());
                    return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
                }
            } catch (RuntimeServiceException e) {
                //TODO POST METHOD USED
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.index");
    }
}

