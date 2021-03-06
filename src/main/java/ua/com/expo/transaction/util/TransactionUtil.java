package ua.com.expo.transaction.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.persistence.connection.ConnectionPool;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.exception_draft.RuntimeSqlException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionUtil {

    /**
     * ThreadLocal is conveniently used to provide each thread with its own instance of a database connection.
     * Each thread that accesses one (via its get or set method) has its own, independently initialized copy of the variable
     * Because the specification does not explicitly say that classes that implement the Connection interface must be thread safe,
     * it is reasonable to isolate each instance of the connection in each thread.
     */

    private static final ThreadLocal<ConnectionWrapper> threadLocalConnection = new ThreadLocal<>();
    private final ConnectionPool connectionPool;
    private static final Logger LOGGER = LogManager.getLogger(TransactionUtil.class.getName());

    private TransactionUtil() {
        this.connectionPool = ConnectionPool.getInstance();
    }

    private static class Holder {
        static TransactionUtil INSTANCE = new TransactionUtil();
    }

    public static TransactionUtil getInstance() {
        return Holder.INSTANCE;
    }

    public void startTransaction() {
        ConnectionWrapper cw = threadLocalConnection.get();
        LOGGER.info("Transaction started");
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
        LOGGER.info("Transaction finished");
        if (Objects.isNull(cw)) {
            throw new RuntimeSqlException("Connection is null");
        }
        try {
            cw.getConnection().close();

        } catch (SQLException e) {
            throw new RuntimeSqlException(e);
        }
        threadLocalConnection.set(null);
    }

    public void rollback() {
        ConnectionWrapper cw = threadLocalConnection.get();
        LOGGER.info("Transaction failed: rollback() invocation");
        if (Objects.isNull(cw)) {
            throw new RuntimeSqlException();
        }
        try {
            cw.getConnection().rollback();
            LOGGER.info("rollback() succeeded");
        } catch (SQLException e) {
            throw new RuntimeSqlException(e);
        }
    }

    public void commit() {
        ConnectionWrapper cw = threadLocalConnection.get();
        LOGGER.info("Transaction succeeded: commit() invocation");
        if (Objects.isNull(cw)) {
            throw new RuntimeSqlException("Connection is null");
        }
        try {
            cw.getConnection().commit();
            LOGGER.info("commit() succeeded");
        } catch (SQLException e) {
            throw new RuntimeSqlException(e);
        }
    }

    public ConnectionWrapper getConnection() {
        ConnectionWrapper cw = threadLocalConnection.get();
        LOGGER.info("Connection successfully received from ThreadLocal<ConnectionWrapper>");
        if (Objects.nonNull(cw)) {
            return cw;
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
