package ua.com.expo.command.commandImpl;

import ua.com.expo.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class SumAllTicketsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        return null;
    }
}
