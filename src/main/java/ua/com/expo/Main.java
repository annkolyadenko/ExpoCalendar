package ua.com.expo;

import ua.com.expo.entity.Ticket;
import ua.com.expo.persistence.dao.daoImpl.MySqlTicketDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MySqlTicketDao dao = new MySqlTicketDao();
        try {
            List<Ticket> tickets = dao.findAllTicketsByUserId(7L);
            System.out.println(tickets);
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

