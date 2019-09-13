package ua.com.expo.controller.context.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
       //logging
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        //logging
    }
}
