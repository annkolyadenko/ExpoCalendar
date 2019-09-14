package ua.com.expo.persistence.connection;

import com.mysql.cj.jdbc.MysqlDataSource;
import ua.com.expo.file.DataBaseSchema;
import ua.com.expo.util.resource.ConfigurationManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Singleton class for providing various types of DB connection:
 * - JNDI Connection Pool;
 * - tests-purposed connection from properties file.
 */
public class ConnectionPoolManager {

    private static volatile ConnectionPoolManager INSTANCE;
    private final DataSource dataSource;
    /*private static final Logger rootLogger = LogManager.getLogger();*/

    enum ConnectionType {
        JNDI, PROPERTIES
    }

    private ConnectionPoolManager(ConnectionType type) throws NamingException, IOException {

        switch (type) {
            case JNDI:
                dataSource = initFromJNDI();
                break;
            case PROPERTIES:
                dataSource = initFromProperties();
                break;
            default:
                throw new EnumConstantNotPresentException(type.getClass(), "during initialization of ConnectionPoolManager");
        }
    }

    public static ConnectionPoolManager getInstance() throws IOException, NamingException {
        if (INSTANCE == null) {
            synchronized (ConnectionPoolManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPoolManager(ConnectionType.PROPERTIES);
                }
            }
        }
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static DataSource initFromJNDI() throws NamingException {
        Context initialContext = new InitialContext();
        Context envContext = (Context) initialContext.lookup("java:/comp/env");
        return (DataSource) envContext.lookup(DataBaseSchema.name);
    }

    private static MysqlDataSource initFromProperties() throws IOException {
        MysqlDataSource mysqlDs = new MysqlDataSource();

        mysqlDs.setUrl(ConfigurationManager.DB_MANAGER.getProperty("jdbc.url"));
        mysqlDs.setUser(ConfigurationManager.DB_MANAGER.getProperty("jdbc.user"));
        mysqlDs.setPassword(ConfigurationManager.DB_MANAGER.getProperty("jdbc.password"));

        return mysqlDs;
    }

    public static Connection getSimpleConnection() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = null;

        String url = ConfigurationManager.DB_MANAGER.getProperty("jdbc.url");
        String user = ConfigurationManager.DB_MANAGER.getProperty("jdbc.user");
        String password = ConfigurationManager.DB_MANAGER.getProperty("jdbc.password");
        connection = DriverManager.getConnection(url, user, password);
        if (Objects.isNull(connection)) {
            throw new IllegalStateException("NO CONNECTION CREATED!");
        }
        System.out.println("Connection established");
        return connection;
    }

    public void close(Connection cn) throws SQLException {
        if (Objects.nonNull(cn))
            cn.close();
    }
}
