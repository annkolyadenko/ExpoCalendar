package ua.com.expo.persistence.dao;

import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITicketDao {

    Long sumPurchasedTicketsByExpoId(Long id);

    List<Ticket> findAllTicketsByUserId(Long id);

    boolean save(Ticket ticket);

    Map<Expo, Long> sumAllPurchasedTickets();

}
