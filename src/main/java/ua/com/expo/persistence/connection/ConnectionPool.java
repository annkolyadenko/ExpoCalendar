package ua.com.expo.persistence.connection;

import ua.com.expo.exceptions.RuntimeSqlException;
import ua.com.expo.files.DataBaseSchema;

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

    ConnectionPool() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup(DataBaseSchema.name);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeSqlException("Якось так");
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}



