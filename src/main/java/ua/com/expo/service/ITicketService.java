package ua.com.expo.service;

import ua.com.expo.entity.Ticket;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface ITicketService {

    Ticket purchaseTicket(Long userId, Long expoId, Long ticketsAmount) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException, InvalidKeySpecException, IOException;
}
