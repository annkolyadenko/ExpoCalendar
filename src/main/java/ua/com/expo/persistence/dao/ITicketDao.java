package ua.com.expo.persistence.dao;

import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ITicketDao {

    Long countPurchasedTicketsByExpoId(Long id);

    List<Ticket> findAllTicketsByUserId(Long id);

    boolean save(Ticket ticket);

    Map<Expo, Long> countAllPurchasedTickets();

    LinkedHashMap<Expo, Long> countAllPurchasedTicketsPageable(Integer offset, Integer limit);

    Integer findNumberOfRowsByUserId(Long id);

    List<Ticket> findAllTicketsByUserIdPageable(Long id, Integer offset, Integer limit);

}
