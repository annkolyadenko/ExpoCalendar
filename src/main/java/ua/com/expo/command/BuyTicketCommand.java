package ua.com.expo.command;

import ua.com.expo.utils.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

public class BuyTicketCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        Enumeration attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            System.out.println(name+"HHH");
        }
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            System.out.println(name+"HOH");
        }
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.ticket");
    }
}
