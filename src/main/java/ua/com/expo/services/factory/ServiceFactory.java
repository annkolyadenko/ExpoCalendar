package ua.com.expo.services.factory;

import ua.com.expo.services.servicesImpl.*;

/**
 * Static field Singleton for Service factory
 * Eager initialization
 * If the program will always need an instance, or if the cost of creating the instance is not too large
 * in terms of time/resources, the programmer can switch to eager initialization, which always creates an instance
 * when the class is loaded into the JVM.
 */
public class ServiceFactory {

    private static final RoleService roleService = new RoleService();
    private static final UserService userService = new UserService();
    private static final ThemeService themeService = new ThemeService();
    private static final ExpoService expoService = new ExpoService();
    private static final TicketService ticketService = new TicketService();

    public static RoleService getRoleService() {
        return roleService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static ThemeService getThemeService() {
        return themeService;
    }

    public static ExpoService getExpoService() {
        return expoService;
    }

    public static TicketService getTicketService() {
        return ticketService;
    }
}
