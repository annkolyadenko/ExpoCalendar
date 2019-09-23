package ua.com.expo.persistence.dao;

import ua.com.expo.entity.Showroom;

import java.util.List;
import java.util.Optional;

public interface IShowroomDao {

    List<Showroom> findAll();

    Optional<Showroom> findShowroomById(Long id);

    boolean save(Showroom showroom);

}
