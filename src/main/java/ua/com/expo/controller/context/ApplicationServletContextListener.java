package ua.com.expo.controller.context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.service.factory.ServiceFactory;
import ua.com.expo.transaction.util.TransactionUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationServletContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationServletContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.info("ServletContext initialized");
        ServletContext servletContext = event.getServletContext();
        Context.getInstance().setServletContext(servletContext);
        servletContext.setAttribute("mySqlDaoFactory", MySqlDaoFactory.getInstance());
        servletContext.setAttribute("serviceFactory", ServiceFactory.getInstance());
        servletContext.setAttribute("transactionUtil", TransactionUtil.getInstance());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("ServletContext destroyed");
    }
}
