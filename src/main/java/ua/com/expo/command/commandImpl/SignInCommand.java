package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.UserDto;
import ua.com.expo.exception_draft.RuntimeServiceException;
import ua.com.expo.service.serviceImpl.UserService;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.validator.IRequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.validator.impl.RequestValidator;


public class SignInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class.getName());
    private final UserService userService;

    public SignInCommand() {
        this.userService = Context.getInstance().getServiceFactory().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IRequestValidator validator = RequestValidator.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (validator.emailValidate(email) && validator.passwordValidate(password)) {
            try {
                UserDto userDto = userService.signInUser(email, password);
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", userDto);
                session.setAttribute("locale", userDto.getLanguage());
                LOGGER.debug("LOCALE: " + userDto.getLanguage());
                return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");

            } catch (RuntimeServiceException e) {
                setErrorMessage(request, e);
            }
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.index");
    }

    private void setErrorMessage(HttpServletRequest request, RuntimeServiceException e) {
        request.setAttribute("isError", true);
        request.setAttribute("errorMessage", e.getMessage());
    }
}

