package ua.com.expo.transaction_draft;

import ua.com.expo.persistence.connection.ConnectionPool;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.exception.RuntimeSqlException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionUtil {

    /**
     * ThreadLocal is conveniently used to provide each thread with its own instance of a database connection.
     * Because the specification does not explicitly say that classes that implement the Connection interface must be thread safe,
     * it is reasonable to isolate each instance of the connection in each thread.
     */

    private static final ThreadLocal<ConnectionWrapper> threadLocalConnection = new ThreadLocal<>();
    private final ConnectionPool connectionPool;

    public TransactionUtil() {
        this.connectionPool = ConnectionPool.getInstance();
    }

    public void startTransaction() {
        ConnectionWrapper cw = threadLocalConnection.get();
        if (Objects.nonNull(cw)) {
            throw new RuntimeSqlException();
        }
        try {
            Connection connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            cw = new ConnectionWrapper(connection);
            threadLocalConnection.set(cw);
        } catch (SQLException e) {
            throw new RuntimeSqlException();
        }
    }

    public void endTransaction() {
        ConnectionWrapper cw = threadLocalConnection.get();
        if (Objects.isNull(cw)) {
            throw new RuntimeSqlException();
        }
        try {
            cw.getConnection().close();

        } catch (SQLException e) {
            throw new RuntimeSqlException();
        }
        threadLocalConnection.set(null);
    }

    public void rollback() {
        ConnectionWrapper cw = threadLocalConnection.get();
        if (Objects.isNull(cw)) {
            throw new RuntimeSqlException();
        }
        try {
            cw.getConnection().rollback();

        } catch (SQLException e) {
            throw new RuntimeSqlException();
        }
    }

    public void commit() {
        ConnectionWrapper cw = threadLocalConnection.get();
        if (Objects.isNull(cw)) {
            throw new RuntimeSqlException();
        }
        try {
            cw.getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeSqlException();
        }
    }

    public ConnectionWrapper getConnection() {
        ConnectionWrapper cw = threadLocalConnection.get();
        if (Objects.nonNull(cw)) {
            throw new RuntimeSqlException();
        }
        try {
            Connection connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            cw = new ConnectionWrapper(connection);

        } catch (SQLException e) {
            throw new RuntimeSqlException();
        }
        return cw;
    }
}
