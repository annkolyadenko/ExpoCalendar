package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.UserDto;
import ua.com.expo.exception_draft.RuntimeServiceException;
import ua.com.expo.service.serviceImpl.UserService;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.validator.IRequestValidator;
import ua.com.expo.util.validator.impl.RequestValidator;


public class SignUpCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SignUpCommand.class.getName());
    private final UserService userService;

    public SignUpCommand() {
        this.userService = Context.getInstance().getServiceFactory().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestValidator requestParametersValidator = RequestValidator.getInstance();
        HttpSession session = request.getSession();
        String name = request.getParameter("userName");
        String email = request.getParameter("email");
        String language = (String) session.getAttribute("locale");
        LOGGER.debug("LOCALE :" + language);
        String password = request.getParameter("password");
        if (requestParametersValidator.emailPasswordValidate(email, password) & requestParametersValidator.isNotNull(name)) {
            try {
                UserDto userDto = userService.signUpUser(name, email, language, password);
                if (Objects.nonNull(userDto)) {
                    session.setAttribute("authorizedUser", userDto);
                    return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
                }
            } catch (RuntimeServiceException e) {
                //TODO NEED TO SOLVE SEND-REDIRECT PROBLEM
                LOGGER.debug("CAUGHT in SignUp Command" + e.getMessage());
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.index");
    }
}
