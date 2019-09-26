package ua.com.expo.persistence.dao.factory;

import ua.com.expo.persistence.dao.*;
import ua.com.expo.persistence.dao.daoImpl.*;

/**
 * Initialization-on-demand holder idiom Singleton
 * lazy initialization, based on the class initialization procedure which is guaranteed by Java Language Specification
 * to be thread-safe.
 */
public class MySqlDaoFactory extends AbstractDaoFactory {

    private final IRoleDao roleDao;
    private final IUserDao userDao;
    private final IThemeDao themeDao;
    private final IExpoDao expoDao;
    private final IShowroomDao showroomDao;
    private final IPaymentDao paymentDao;
    private final ITicketDao ticketDao;

    private MySqlDaoFactory() {
        this.roleDao = new MySqlRoleDao();
        this.userDao = new MySqlUserDao();
        this.themeDao = new MySqlThemeDao();
        this.expoDao = new MySqlExpoDao();
        this.showroomDao = new MySqlShowroomDao();
        this.paymentDao = new MySqlPaymentDao();
        this.ticketDao = new MySqlTicketDao();
    }

    private static class MySqlDaoFactoryHolder {
        static MySqlDaoFactory mySqlFactory = new MySqlDaoFactory();
    }

    public static MySqlDaoFactory getInstance() {
        return MySqlDaoFactoryHolder.mySqlFactory;
    }

    @Override
    public IRoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public IThemeDao getThemeDao() {
        return themeDao;
    }

    @Override
    public IExpoDao getExpoDao() {
        return expoDao;
    }

    @Override
    public IShowroomDao getShowroomDao() {
        return showroomDao;
    }

    @Override
    public IPaymentDao getPaymentDao() {
        return paymentDao;
    }

    @Override
    public ITicketDao getTicketDao() {
        return ticketDao;
    }
}
