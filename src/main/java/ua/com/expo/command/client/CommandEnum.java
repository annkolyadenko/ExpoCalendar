package ua.com.expo.command.client;

import ua.com.expo.command.*;
import ua.com.expo.command.commandImpl.*;

/**
 * Enum Singleton Command factory
 */
public enum CommandEnum {

    PURCHASE_TICKET(new PurchaseTicketCommand()),
    GET_ALL_THEMES(new GetAllThemesCommand()),
    GET_ALL_EXPO_BY_THEME_ID_AND_DATE(new GetAllExpoByThemeCommand()),
    GET_ALL_TICKETS_BY_USER_ID(new GetAllTicketsCommand()),
    GET_ALL_SHOWROOM(new GetAllShowroomCommand()),
    GET_ALL_EXPO_BY_SHOWROOM_ID(new GetAllExpoByShowroomCommand()),
    LOGIN(new SignInCommand()),
    LOGOUT(new SignOutCommand()),
    SUM_ALL_TICKETS_BY_EXPO_ID(new SumAllTicketsCommand()),
    RETURN_TO_HOMEPAGE(new ReturnToHomePageCommand()),
    REGISTRATION(new SignUpCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}