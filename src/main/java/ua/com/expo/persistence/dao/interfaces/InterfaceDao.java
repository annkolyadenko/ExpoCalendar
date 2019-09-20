package ua.com.expo.persistence.dao.interfaces;

import ua.com.expo.entity.Entity;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public interface InterfaceDao<K, T extends Entity> {

    List<T> findAll() throws SQLException, IOException, ClassNotFoundException;

    T findEntityById(K id) throws SQLException, IOException, ClassNotFoundException;

    boolean create(T entity) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException;

    T update(T entity);

    //TODO!
    default void close(Statement st) throws SQLException {
        if (Objects.nonNull(st))
            st.close();
    }

    //TODO!
    default void close(ResultSet rs) throws SQLException {
        if (Objects.nonNull(rs))
            rs.close();
    }
}
