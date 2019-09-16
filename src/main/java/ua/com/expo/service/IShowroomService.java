package ua.com.expo.service;

import ua.com.expo.entity.Showroom;

import java.sql.SQLException;
import java.util.List;

public interface IShowroomService {

    List<Showroom> findAllShowroom() throws SQLException;
}
