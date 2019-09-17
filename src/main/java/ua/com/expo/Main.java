package ua.com.expo;

import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Ticket;
import ua.com.expo.persistence.dao.daoImpl.MySqlExpoDao;
import ua.com.expo.persistence.dao.daoImpl.MySqlShowroomDao;
import ua.com.expo.persistence.dao.daoImpl.MySqlTicketDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

       /* MySqlTicketDao dao = new MySqlTicketDao();
        try {
            List<Ticket> tickets = dao.findAllTicketsByUserId(7L);
            System.out.println(tickets);
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        MySqlShowroomDao dao1 = new MySqlShowroomDao();
        try {
            List<Showroom> showrooms = dao1.findAll();
            for (Showroom showroom : showrooms) {
                System.out.println(showroom);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }*/
       /* MySqlExpoDao dao2 = new MySqlExpoDao();
        List<Expo> expos = dao2.findAllExpoByShowroomId(3L);
        for (Expo expo : expos) {
            System.out.println(expo);
        }*/
       MySqlTicketDao mySqlTicketDao = new MySqlTicketDao();
       Long result = mySqlTicketDao.sumPurchasedTicketsByExpoId(1L);
        System.out.println(result);
    }
}

