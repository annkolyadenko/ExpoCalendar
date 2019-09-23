package ua.com.expo.persistence.dao;

import ua.com.expo.entity.Theme;

import java.util.List;
import java.util.Optional;

public interface IThemeDao {

    List<Theme> findAll();

    Optional<Theme> findThemeById(Long id);

    boolean save(Theme theme);

}
