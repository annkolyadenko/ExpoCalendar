package ua.com.expo.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ActionCommand interface
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);

    //TODO INTO COMMAND IMPLEMENTATION
    static Map<String, String> extractParameters(HttpServletRequest request) {
        Map<String, String> requestParameters = new HashMap<>();

        List<String> parameters = Collections.list(request.getParameterNames());

        for (String parameter : parameters) {
            requestParameters.put(parameter, request.getParameter(parameter));
        }
        return requestParameters;
    }
}
