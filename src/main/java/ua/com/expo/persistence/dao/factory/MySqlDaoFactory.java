package ua.com.expo.persistence.dao.factory;

import ua.com.expo.persistence.dao.daoImpl.*;
import ua.com.expo.persistence.dao.interfaces.*;

/**
 * Initialization-on-demand holder idiom Singleton
 * lazy initialization, based on the class initialization procedure which is guaranteed by Java Language Specification
 * to be thread-safe.
 */
public class MySqlDaoFactory extends AbstractDaoFactory {

    private MySqlDaoFactory() {
    }

    private static class MySqlDaoFactoryHolder {
        public static MySqlDaoFactory mySqlFactory = new MySqlDaoFactory();
    }

    public static MySqlDaoFactory getInstance() {
        return MySqlDaoFactoryHolder.mySqlFactory;
    }

    @Override
    public IRoleDao getRoleDao() {
        return new MySqlRoleDao();
    }

    @Override
    public IUserDao getUserDao() {
        return new MySqlUserDao();
    }

    @Override
    public IThemeDao getThemeDao() {
        return new MySqlThemeDao();
    }

    @Override
    public IExpoDao getExpoDao() {
        return new MySqlExpoDao();
    }

    @Override
    public IShowroomDao getShowroomDao() {
        return new MySqlShowroomDao();
    }


}
