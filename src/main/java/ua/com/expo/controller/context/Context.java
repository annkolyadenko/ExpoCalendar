package ua.com.expo.controller.context;

import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.transaction.util.TransactionUtil;

import javax.servlet.ServletContext;

public class Context {

    private static ServletContext servletContext;

    private Context() {
    }

    private static class ServiceFactoryHolder {
        static Context context = new Context();
    }

    public static Context getInstance() {
        return ServiceFactoryHolder.context;
    }

    void setServletContext(ServletContext context) {
        servletContext = context;
    }

    public MySqlDaoFactory getMySqlDaoFactory() {
        return (MySqlDaoFactory) servletContext.getAttribute("mySqlDaoFactory");
    }

    public ServiceFactory getServiceFactory() {
        return (ServiceFactory) servletContext.getAttribute("serviceFactory");
    }

    public TransactionUtil getTransactionUtil() {
        return (TransactionUtil) servletContext.getAttribute("transactionUtil");
    }
}
