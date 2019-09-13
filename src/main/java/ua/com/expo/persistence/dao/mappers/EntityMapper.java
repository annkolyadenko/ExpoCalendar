package ua.com.expo.persistence.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {

    T extractFromResultSet(ResultSet resultSet) throws SQLException;
}
