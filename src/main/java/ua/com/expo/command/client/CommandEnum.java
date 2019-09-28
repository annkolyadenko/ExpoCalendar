package ua.com.expo.command.client;

import ua.com.expo.command.*;
import ua.com.expo.command.commandImpl.*;

/**
 * Enum Singleton Command factory
 */
public enum CommandEnum {
    ADD_NEW_THEME(new AddNewThemeCommand()),
    ADD_NEW_EXPO(new AddNewExpoCommand()),
    APPROVE_NEW_THEME(new ApproveNewThemeCommand()),
    APPROVE_NEW_EXPO(new ApproveNewExpoCommand()),
    GET_ALL_THEMES(new GetAllThemesCommand()),
    GET_ALL_EXPO_BY_THEME_ID_AND_DATE(new GetAllExpoByThemeCommand()),
    GET_ALL_TICKETS_BY_USER_ID(new GetAllTicketsCommand()),
    GET_ALL_SHOWROOM(new GetAllShowroomCommand()),
    GET_ALL_EXPO_BY_SHOWROOM_ID(new GetAllExpoByShowroomCommand()),
    GET_STATS_BY_EXPO(new GetStatsByExpoCommand()),
    LOCALIZATION(new LocalizationCommand()),
    LOGIN(new SignInCommand()),
    LOGOUT(new SignOutCommand()),
    PURCHASE_TICKET(new PurchaseTicketCommand()),
    RETURN_TO_HOMEPAGE(new ReturnToHomePageCommand()),
    REGISTRATION(new SignUpCommand()),
    SUM_ALL_TICKETS_BY_EXPO_ID(new SumAllTicketsCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}