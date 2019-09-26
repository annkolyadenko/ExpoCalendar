package ua.com.expo.service.factory;

import ua.com.expo.service.serviceImpl.*;

public class ServiceFactory {

    private final UserService userService;
    private final ThemeService themeService;
    private final ExpoService expoService;
    private final TicketService ticketService;
    private final ShowroomService showroomService;

    private ServiceFactory() {
        this.userService = new UserService();
        this.themeService = new ThemeService();
        this.expoService = new ExpoService();
        this.ticketService = new TicketService();
        this.showroomService = new ShowroomService();
    }

    private static class ServiceFactoryHolder {
        static ServiceFactory serviceFactory = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.serviceFactory;
    }

    public UserService getUserService() {
        return userService;
    }

    public ThemeService getThemeService() {
        return themeService;
    }

    public ExpoService getExpoService() {
        return expoService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public ShowroomService getShowroomService() {
        return showroomService;
    }
}
