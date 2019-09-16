package ua.com.expo.persistence.dao.interfaces;

import ua.com.expo.entity.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITicketDao extends InterfaceDao<Long, Ticket> {

    Map<Long, Long> sumPurchasedTicketsByExpoId(Long expoId);

    List<Ticket> findAllTicketsByUserId(Long id) throws SQLException, IOException, ClassNotFoundException;

}
