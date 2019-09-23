package ua.com.expo.persistence.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.exception_draft.RuntimeSqlException;

public class ConnectionWrapper {

    private final Connection connection;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionWrapper.class.getName());

    public ConnectionWrapper(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement createPreparedStatement(String sql) {
        PreparedStatement ps = null;
        if (Objects.nonNull(connection)) {
            try {
                ps = connection.prepareStatement(sql);
            } catch (SQLException e) {
                LOGGER.error("Can't create PreparedStatement", e);
                throw new RuntimeSqlException(e);
            }
        }
        return ps;
    }

    public PreparedStatement createPreparedStatementWithGeneratedKey(String sql) {
        PreparedStatement ps = null;
        if (Objects.nonNull(connection)) {
            try {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                LOGGER.error("Can't create PreparedStatement", e);
                throw new RuntimeSqlException(e);
            }
        }
        return ps;
    }


    public Connection getConnection() {
        return connection;
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if (Objects.nonNull(ps)) {
            try {
                ps.close();
            } catch (SQLException e) {
                LOGGER.error("Can't close connection", e);
                throw new RuntimeSqlException(e);
            }
        }
    }

    public void closeConnection() {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Can't close connection", e);
                throw new RuntimeSqlException(e);
            }
        }
    }
}
