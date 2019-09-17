package ua.com.expo.persistence.dao.interfaces;

import ua.com.expo.entity.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITicketDao extends InterfaceDao<Long, Ticket> {

    Long sumPurchasedTicketsByExpoId(Long expoId) throws SQLException, IOException, ClassNotFoundException;

    List<Ticket> findAllTicketsByUserId(Long id) throws SQLException, IOException, ClassNotFoundException;

}
