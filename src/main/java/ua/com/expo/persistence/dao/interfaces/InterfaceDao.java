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

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException;

    T update(T entity);

    //TO DO!
    default void close(Statement st) throws SQLException {
        if (Objects.nonNull(st))
            st.close();
    }

    //TO DO!
    default void close(ResultSet rs) throws SQLException {
        if (Objects.nonNull(rs))
            rs.close();
    }
}
