package ua.com.expo.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * ActionCommand interface
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException;
}
