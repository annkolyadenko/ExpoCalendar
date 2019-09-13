package ua.com.expo.model.dao.factory;

import ua.com.expo.model.dao.interfaces.*;


public abstract class AbstractDaoFactory {

    public abstract IRoleDao getRoleDao();

    public abstract IUserDao getUserDao();

    public abstract IThemeDao getThemeDao();

    public abstract IExpoDao getExpoDao();

    public abstract IShowroomDao getShowroomDao();
}
