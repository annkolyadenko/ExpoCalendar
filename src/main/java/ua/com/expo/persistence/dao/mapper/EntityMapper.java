package ua.com.expo.persistence.dao.mapper;

import java.sql.ResultSet;

/**
 * ResultSet mapper for Entity subclasses
 **/

public interface EntityMapper<T> {

    T extractFromResultSet(ResultSet resultSet);
}
