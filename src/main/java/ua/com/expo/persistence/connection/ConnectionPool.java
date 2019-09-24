package ua.com.expo.persistence.connection;

import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.file.DataBaseSchema;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Enum Singleton for JNDI Connection Pool
 */
public enum ConnectionPool {
    INSTANCE;
    private DataSource dataSource;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());

    ConnectionPool() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup(DataBaseSchema.name);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeSqlException(e);
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            LOGGER.info("Connection established");
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        }
    }
}



