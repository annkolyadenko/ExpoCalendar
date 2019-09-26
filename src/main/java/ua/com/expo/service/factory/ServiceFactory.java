package ua.com.expo.service.factory;

import ua.com.expo.service.serviceImpl.*;

public class ServiceFactory {

    private final UserService userService;
    private final VisitorService visitorService;
    private final AdminService adminService;


    private ServiceFactory() {
        this.userService = new UserService();
        this.visitorService = new VisitorService();
        this.adminService = new AdminService();
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

    public VisitorService getVisitorService() {
        return visitorService;
    }

    public AdminService getAdminService() {
        return adminService;
    }
}
