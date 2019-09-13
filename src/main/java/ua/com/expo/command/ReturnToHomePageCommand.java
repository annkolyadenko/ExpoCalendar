package ua.com.expo.command;

import ua.com.expo.entity.User;
import ua.com.expo.utils.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ReturnToHomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        return ConfigurationManager.PATH_MANAGER.getProperty("path.page.main");
    }
}
