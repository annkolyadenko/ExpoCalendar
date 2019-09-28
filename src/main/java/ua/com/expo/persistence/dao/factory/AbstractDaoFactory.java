package ua.com.expo.persistence.dao.factory;

import ua.com.expo.persistence.dao.*;


public abstract class AbstractDaoFactory {

    public abstract IUserDao getUserDao();

    public abstract IThemeDao getThemeDao();

    public abstract IExpoDao getExpoDao();

    public abstract IShowroomDao getShowroomDao();

    public abstract IPaymentDao getPaymentDao();

    public abstract ITicketDao getTicketDao();
}
